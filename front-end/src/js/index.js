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
        if (element.id === "dot" && numberOne && !dotNumberOne) {
            numberOne = numberOne + "."
            inputOperation.value = inputOperation.value + ","
            dotNumberOne = true;
        }
        if (element.id === "dot" && numberTwo && !dotNumberTwo) {
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
            if (Number.isInteger(numberOne) && Number.isInteger(numberTwo)) {
                inputOperation.value = parseInt(numberOne) * parseInt(numberTwo)
            } else {
                inputOperation.value = parseFloat(numberOne) * parseFloat(numberTwo)
            }
            break;
        case "/":
            if (Number.isInteger(numberOne) && Number.isInteger(numberTwo)) {
                inputOperation.value = parseInt(numberOne) / parseInt(numberTwo)
            } else {
                inputOperation.value = parseFloat(numberOne) / parseFloat(numberTwo)
            }
            break;
        case "+":
        if (Number.isInteger(numberOne) && Number.isInteger(numberTwo)) {
            inputOperation.value = parseInt(numberOne) + parseInt(numberTwo)
        } else {
            inputOperation.value = parseFloat(numberOne) + parseFloat(numberTwo)
        }
            break;
        case "-":
            if (Number.isInteger(numberOne) && Number.isInteger(numberTwo)) {
                inputOperation.value = parseInt(numberOne) - parseInt(numberTwo)
            } else {
                inputOperation.value = parseFloat(numberOne) - parseFloat(numberTwo)
            }
            break;
    }
    inputOperation.value =  String(inputOperation.value).replace(".", ",")
    numberOne = inputOperation.value
    numberTwo = undefined
    console.log({ numberOne, numberTwo})
}