import React, { Component } from 'react';
import '../style.css';
interface IState {
  outputOperation: string;
  firstNumber: string | null; //int or float
  secondNumber: string | null; //int or float
  operation: string | null;
  hastDotFirstNumber: boolean;
  hasDotSecondNumber: boolean;
}
export default class Calculator extends Component<object, IState> {
  constructor(props: object) {
    super(props);
    this.state = {
      outputOperation: '',
      firstNumber: null,
      secondNumber: null,
      operation: null,
      hastDotFirstNumber: false,
      hasDotSecondNumber: false
    };
  }

  inputNumberInOutputOperation = (e: React.MouseEvent<HTMLElement>): void => {
    let firstNumberResult: string | null,
      secondNumberResult: string | null,
      outputOperationResult: string;
    firstNumberResult = this.state.firstNumber;
    secondNumberResult = this.state.secondNumber;
    outputOperationResult = '';
    //INPUTS OF FIRST NUMBER
    if (this.state.firstNumber && !this.state.operation && !this.state.secondNumber) {
      firstNumberResult = this.state.firstNumber + e.currentTarget.textContent;
      outputOperationResult = firstNumberResult;
    }
    if (!this.state.firstNumber && !this.state.secondNumber) {
      firstNumberResult = e.currentTarget.textContent;
      outputOperationResult = firstNumberResult !== null ? firstNumberResult : '';
    }

    //INPUTS OF SECOND NUMBER
    if (this.state.firstNumber && this.state.secondNumber) {
      secondNumberResult = this.state.secondNumber + e.currentTarget.textContent;
      outputOperationResult = this.state.outputOperation + secondNumberResult;
    }

    if (this.state.firstNumber && this.state.operation && !this.state.secondNumber) {
      secondNumberResult = e.currentTarget.textContent;
      outputOperationResult =
        this.state.outputOperation + (secondNumberResult !== null ? secondNumberResult : '');
    }

    console.log({ firstNumberResult, secondNumberResult, outputOperationResult });
    this.setState({
      firstNumber: firstNumberResult,
      secondNumber: secondNumberResult,
      outputOperation: outputOperationResult
    });
  };

  inputOperatorInOutputOperation = (e: React.MouseEvent<HTMLElement>): void => {
    if (this.state.firstNumber && !this.state.operation && !this.state.secondNumber) {
      this.setState({
        operation: e.currentTarget.textContent,
        outputOperation: this.state.outputOperation + e.currentTarget.textContent
      });
    }
  };
  resolveOperation = (): void => {
    //Verify if is possible resolve operation
    if (this.state.firstNumber && this.state.secondNumber && this.state.operation) {
      let outputOperationResult = '';
      switch (this.state.operation) {
        case 'x':
          outputOperationResult = String(
            parseInt(this.state.firstNumber) * parseInt(this.state.secondNumber)
          );
          break;
        case '/':
          outputOperationResult = String(
            parseInt(this.state.firstNumber) / parseInt(this.state.secondNumber)
          );
          break;
        case '+':
          outputOperationResult = String(
            parseInt(this.state.firstNumber) + parseInt(this.state.secondNumber)
          );
          break;
        case '-':
          outputOperationResult = String(
            parseInt(this.state.firstNumber) - parseInt(this.state.secondNumber)
          );
          break;
      }
      this.setState({
        outputOperation: outputOperationResult,
        firstNumber: outputOperationResult,
        secondNumber: null,
        operation: null
      });
    }
  };

  render() {
    return (
      <>
        <header>
          <h1>CALCULATOR</h1>
        </header>
        <div id="calc">
          <output name="Operation Input" id="inputOperation">
            {this.state.outputOperation}
          </output>
          <table>
            <tbody>
              <tr>
                <td>
                  <button onClick={this.inputNumberInOutputOperation.bind(this)} className="number">
                    7
                  </button>
                </td>
                <td>
                  <button onClick={this.inputNumberInOutputOperation.bind(this)} className="number">
                    8
                  </button>
                </td>
                <td>
                  <button onClick={this.inputNumberInOutputOperation.bind(this)} className="number">
                    9
                  </button>
                </td>
                <td>
                  <button
                    onClick={this.inputOperatorInOutputOperation.bind(this)}
                    className="operation">
                    x
                  </button>
                </td>
              </tr>
              <tr>
                <td>
                  <button onClick={this.inputNumberInOutputOperation.bind(this)} className="number">
                    4
                  </button>
                </td>
                <td>
                  <button onClick={this.inputNumberInOutputOperation.bind(this)} className="number">
                    5
                  </button>
                </td>
                <td>
                  <button onClick={this.inputNumberInOutputOperation.bind(this)} className="number">
                    6
                  </button>
                </td>
                <td>
                  <button
                    onClick={this.inputOperatorInOutputOperation.bind(this)}
                    className="operation">
                    -
                  </button>
                </td>
              </tr>
              <tr>
                <td>
                  <button onClick={this.inputNumberInOutputOperation.bind(this)} className="number">
                    1
                  </button>
                </td>
                <td>
                  <button onClick={this.inputNumberInOutputOperation.bind(this)} className="number">
                    2
                  </button>
                </td>
                <td>
                  <button onClick={this.inputNumberInOutputOperation.bind(this)} className="number">
                    3
                  </button>
                </td>
                <td>
                  <button
                    onClick={this.inputOperatorInOutputOperation.bind(this)}
                    className="operation">
                    +
                  </button>
                </td>
              </tr>
              <tr>
                <td>
                  <button id="dot">.</button>
                </td>
                <td>
                  <button onClick={this.inputNumberInOutputOperation.bind(this)} className="number">
                    0
                  </button>
                </td>
                <td>
                  <button onClick={this.resolveOperation} id="resolve">
                    =
                  </button>
                </td>
                <td>
                  <button
                    onClick={this.inputOperatorInOutputOperation.bind(this)}
                    className="operation">
                    /
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </>
    );
  }
}
