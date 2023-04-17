const inputOperation = document.querySelector("#inputOperation")
var numberOne, numberTwo, operation;
var dotNumberOne = false;
var dotNumberTwo = false;
const buttonsOperationsAndNumber = document.querySelectorAll(".number,.operation,#dot")
buttonsOperationsAndNumber.forEach((element) => {
    element.addEventListener("click", () => {
        //console.log(element.className)
        if (element.className === "operation" && numberOne && numberTwo && operation) {
            resolveOperation()
        }
        if (element.className === "number"  && numberOne && !operation && !numberTwo) {
            numberOne = numberOne + element.textContent
        }
        if (element.className === "number" && !numberOne && !operation && !numberTwo) {
            numberOne = element.textContent  
        } 
        if (element.className === "operation" && numberOne && !operation && !numberTwo) {
           operation = element.textContent
        }
        if (element.className === "number"  && numberOne && operation && numberTwo) {
            numberTwo = numberTwo + element.textContent
        }
        if (element.className === "number" && numberOne && operation && !numberTwo) {
            numberTwo = element.textContent  
        }
        if (element.id === "dot" && numberOne && !dotNumberOne && !operation) {
            numberOne = numberOne + "."
            inputOperation.value = inputOperation.value + ","
            dotNumberOne = true;
        }
        if (element.id === "dot" && numberTwo && !dotNumberTwo && operation) {
            numberTwo = numberTwo + "."
            dotNumberTwo = true;
            inputOperation.value = inputOperation.value + ","
        }
        if (element.id != "dot" && element.id != "operation") {
            inputOperation.value = inputOperation.value ? inputOperation.value + element.textContent : element.textContent

        }
        console.log({numberOne, numberTwo})
    })
})

document.querySelector("#resolve").addEventListener("click", () => {
    if (numberOne && numberTwo && operation) {
        resolveOperation()
    }
})


const resolveOperation = () => {
    switch (operation) {
        case "x":
            inputOperation.value = (Number.isInteger(numberOne) ? parseInt(numberOne) : parseFloat(numberOne))
                * (Number.isInteger(numberTwo) ? parseInt(numberTwo) : parseFloat(numberTwo))
            break;
        case "/":
            inputOperation.value = (Number.isInteger(numberOne) ? parseInt(numberOne) : parseFloat(numberOne))
            / (Number.isInteger(numberTwo) ? parseInt(numberTwo) : parseFloat(numberTwo))
            break;
        case "+":
            inputOperation.value = (Number.isInteger(numberOne) ? parseInt(numberOne) : parseFloat(numberOne))
                + (Number.isInteger(numberTwo) ? parseInt(numberTwo) : parseFloat(numberTwo))

            break;
        case "-":
            inputOperation.value = (Number.isInteger(numberOne) ? parseInt(numberOne) : parseFloat(numberOne))
            - (Number.isInteger(numberTwo) ? parseInt(numberTwo) : parseFloat(numberTwo))
            break;
    }
    inputOperation.value =  String(inputOperation.value).replace(".", ",")
    numberOne = inputOperation.value
    numberTwo = undefined
    console.log({ numberOne, numberTwo})
}