import React from "react";

function ToDo(props) {
  const statusStyle = {
    complete: "line-through",
  };
  const todosMap =
    props.selectedBucketId === 0
      ? ""
      : props.todos
          .filter((obj) => obj.bucketId === props.selectedBucketId)
          .map((obj) => (
            <div key={obj.todoId} className="todo-item">
              <div className="tooltip">
                <input
                  readOnly={obj.todoStatus ? true : false}
                  style={
                    obj.todoStatus
                      ? { textDecoration: "line-through" }
                      : { textDecoration: "none" }
                  }
                  placeholder=""
                  onChange={(e) => props.handleChangeToDoDesc(e, obj.todoId)}
                  value={obj.todoDesc}
                ></input>
                {obj.todoDesc.trim() === "" ? (
                  ""
                ) : (
                  <span class="tooltiptext">{obj.todoDesc}</span>
                )}
              </div>
              <button onClick={() => props.deleteThisTodo(obj.todoId)}>
                delete
              </button>
              <button onClick={() => props.toggleCompleteStatus(obj.todoId)}>
                {obj.todoStatus ? "mark incomplete" : "mark complete"}
              </button>
            </div>
          ));

  return <React.Fragment> {todosMap}</React.Fragment>;
}

export default ToDo;
