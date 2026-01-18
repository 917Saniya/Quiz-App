import React, { useState } from "react";
import "./App.css";

const questions = [
  {
    question: "What does HTML stand for?",
    options: [
      "Hyper Tool Markup Language",
      "Hyper Text Markup Language",
      "High Text Markup Language",
      "Hyperlinks Text Mark Language"
    ],
    correctAnswer: 1
  },
  {
    question: "Which keyword declares a block-scoped variable in JavaScript?",
    options: ["var", "let", "static", "function"],
    correctAnswer: 1
  },
  {
    question: "Which operator checks value and type?",
    options: ["==", "===", "!=", "="],
    correctAnswer: 1
  },
  {
    question: "String objects in Java are:",
    options: ["Mutable", "Immutable", "Dynamic", "Static"],
    correctAnswer: 1
  },
  {
    question: "Default embedded server in Spring Boot:",
    options: ["Tomcat", "JBoss", "GlassFish", "WebLogic"],
    correctAnswer: 0
  }
];

function App() {
  const [currentQuestion, setCurrentQuestion] = useState(0);
  const [selectedOption, setSelectedOption] = useState(null);
  const [score, setScore] = useState(0);
  const [isFinished, setIsFinished] = useState(false);

  const handleOptionSelect = (index) => {
    setSelectedOption(index);
  };

  const handleNext = () => {
    if (selectedOption === questions[currentQuestion].correctAnswer) {
      setScore((prevScore) => prevScore + 1); 
    }

    setSelectedOption(null);

    if (currentQuestion + 1 < questions.length) {
      setCurrentQuestion((prev) => prev + 1);
    } else {
      setIsFinished(true);
    }
  };

  const handleRestart = () => {
    setCurrentQuestion(0);
    setSelectedOption(null);
    setScore(0);
    setIsFinished(false);
  };

  return (
    <div className="quiz-container">
      <h1>Quiz App</h1>

      {!isFinished ? (
        <div>
          <h2>
            Question {currentQuestion + 1} of {questions.length}
          </h2>
          <p>{questions[currentQuestion].question}</p>

          {questions[currentQuestion].options.map((option, index) => (
            <div key={index} className="option">
              <label>
                <input
                  type="radio"
                  name="option"
                  checked={selectedOption === index}
                  onChange={() => handleOptionSelect(index)}
                />
                {option}
              </label>
            </div>
          ))}

          <button
            onClick={handleNext}
            disabled={selectedOption === null}
            className="next-button"
          >
            Next
          </button>
        </div>
      ) : (
        <div className="result">
          <h2>Quiz Completed!</h2>
          <p>
            Your Score: {score} / {questions.length}
          </p>
          <button onClick={handleRestart}>Restart Quiz</button>
        </div>
      )}
    </div>
  );
}

export default App;