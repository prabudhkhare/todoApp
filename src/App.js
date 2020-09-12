import React from "react";
import "./App.css";
import Bucket from "./todo/bucket";
import ToDo from "./todo/todo";

class App extends React.Component {
  constructor() {
    super();
    this.state = {
      buckets: [{ bucketId: 0, name: "" }],
      todos: [{ bucketId: 0, todoId: 0, todoDesc: "", todoStatus: false }],
      bucketId: 0,
      todoId: 0,
      currentBucketName: "",
      selectedBucketId: 0,
    };
    this.handleBucketNameChange = this.handleBucketNameChange.bind(this);
    this.handleClickCreateNewBucket = this.handleClickCreateNewBucket.bind(
      this
    );
    this.setCurrentBucketId = this.setCurrentBucketId.bind(this);
    this.createNewTodo = this.createNewTodo.bind(this);
    this.deleteThisTodo = this.deleteThisTodo.bind(this);
    this.toggleCompleteStatus = this.toggleCompleteStatus.bind(this);
    this.handleChangeToDoDesc = this.handleChangeToDoDesc.bind(this);
  }

  async setCurrentBucketId(id) {
    await this.setState({
      selectedBucketId: this.state.selectedBucketId === id ? 0 : id,
    });
  }
  async handleBucketNameChange(e) {
    await this.setState({
      currentBucketName: e.target.value,
    });
  }
  async handleClickCreateNewBucket() {
    if (this.state.currentBucketName !== "") {
      let bId = this.state.bucketId;
      const newBID = ++bId;
      const currentBucketName = this.state.currentBucketName;
      await this.setState({
        buckets: [
          ...this.state.buckets,
          {
            bucketId: newBID,
            name: currentBucketName,
          },
        ],
        currentBucketName: "",
        bucketId: newBID,
        selectedBucketId: newBID,
      });
      this.bucketScrollView.scrollIntoView({ behavior: "smooth" });
    }
  }

  scrollToBottom = () => {
    this.messagesEnd.scrollIntoView({ behavior: "smooth" });
  };
  async createNewTodo() {
    let todoId = this.state.todoId;
    const newtodoId = ++todoId;
    await this.setState({
      todos: [
        ...this.state.todos,
        {
          bucketId: this.state.selectedBucketId,
          todoId: newtodoId,
          todoDesc: "",
          todoStatus: false,
        },
      ],
      todoId: newtodoId,
    });
    this.todoScrollView.scrollIntoView({ behavior: "smooth" });
  }

  async deleteThisTodo(todoId) {
    let todo1 = [...this.state.todos];
    const i = todo1.findIndex((obj) => obj.todoId === todoId);
    todo1.splice(i, 1);
    await this.setState({
      todos: todo1,
    });
  }
  async toggleCompleteStatus(todoId) {
    let todo1 = [...this.state.todos];

    const todo2 = todo1.map((obj) =>
      obj.todoId === todoId
        ? {
            bucketId: obj.bucketId,
            todoId: obj.todoId,
            todoDesc: obj.todoDesc,
            todoStatus: !obj.todoStatus,
          }
        : obj
    );

    await this.setState({
      todos: todo2,
    });
  }

  async handleChangeToDoDesc(e, todoId) {
    let todo1 = [...this.state.todos];

    const todo2 = todo1.map((obj) =>
      obj.todoId === todoId
        ? {
            bucketId: obj.bucketId,
            todoId: obj.todoId,
            todoDesc: e.target.value,
            todoStatus: obj.todoStatus,
          }
        : obj
    );

    await this.setState({
      todos: todo2,
    });
  }

  render() {
    return (
      <React.Fragment>
        <div className="App">
          <h1>
            <p>Todo App</p>
          </h1>

          <div className="bucket">
            <div className="bucket-create">
              <input
                onChange={(e) => {
                  this.handleBucketNameChange(e);
                }}
                value={this.state.currentBucketName}
                placeholder="Enter Bucket Name..."
              ></input>

              <button onClick={this.handleClickCreateNewBucket}>
                Create Bucket
              </button>
            </div>
            <Bucket
              setCurrentBucketId={this.setCurrentBucketId}
              buckets={this.state.buckets}
              selectedBucketId={this.state.selectedBucketId}
            />
            <div
              ref={(element) => {
                this.bucketScrollView = element;
              }}
            />
          </div>
          <div className="todo">
            <ToDo
              todos={this.state.todos}
              selectedBucketId={this.state.selectedBucketId}
              deleteThisTodo={this.deleteThisTodo}
              toggleCompleteStatus={this.toggleCompleteStatus}
              handleChangeToDoDesc={this.handleChangeToDoDesc}
            />

            {this.state.selectedBucketId === 0 ? (
              <p className="getStarted">
                Please select/add some Buckets to get started
              </p>
            ) : (
              <div onClick={this.createNewTodo} className="addnewtodo">
                <p>Add New Task</p>
              </div>
            )}
            <div
              ref={(element) => {
                this.todoScrollView = element;
              }}
            />
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default App;
