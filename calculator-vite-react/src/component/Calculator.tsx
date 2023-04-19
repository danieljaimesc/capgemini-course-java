import { Component } from 'react';
import '../style.css';
export default class Calculator extends Component {
  //TODO segmented functions for event onClick for each button
  render() {
    return (
      <>
        <header>
          <h1>CALCULATOR</h1>
        </header>
        <div id="calc">
          <output name="Operation Input" id="inputOperation">
            1
          </output>
          <table>
            <tbody>
              <tr>
                <td>
                  <button className="number">7</button>
                </td>
                <td>
                  <button className="number">8</button>
                </td>
                <td>
                  <button className="number">9</button>
                </td>
                <td>
                  <button className="operation">x</button>
                </td>
              </tr>
              <tr>
                <td>
                  <button className="number">4</button>
                </td>
                <td>
                  <button className="number">5</button>
                </td>
                <td>
                  <button className="number">6</button>
                </td>
                <td>
                  <button className="operation">-</button>
                </td>
              </tr>
              <tr>
                <td>
                  <button className="number">1</button>
                </td>
                <td>
                  <button className="number">2</button>
                </td>
                <td>
                  <button className="number">3</button>
                </td>
                <td>
                  <button className="operation">+</button>
                </td>
              </tr>
              <tr>
                <td>
                  <button id="dot">,</button>
                </td>
                <td>
                  <button className="number">0</button>
                </td>
                <td>
                  <button id="resolve">=</button>
                </td>
                <td>
                  <button className="operation">/</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </>
    );
  }
}
