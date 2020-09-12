(this.webpackJsonptodo=this.webpackJsonptodo||[]).push([[0],{11:function(e,t,a){e.exports=a(19)},16:function(e,t,a){},18:function(e,t,a){},19:function(e,t,a){"use strict";a.r(t);var n=a(0),o=a.n(n),c=a(6),r=a.n(c),s=(a(16),a(4)),u=a(1),d=a.n(u),i=a(3),l=a(7),h=a(8),k=a(2),m=a(10),p=a(9);a(18);var b=function(e){var t=e.buckets.map((function(t){return 0===t.bucketId?"":o.a.createElement("div",{onClick:function(){return e.setCurrentBucketId(t.bucketId)},key:t.bucketId,className:"bucket-buttons"},o.a.createElement("div",{className:e.selectedBucketId===t.bucketId?"bucket-item active":"bucket-item"},o.a.createElement("p",null,t.name)))}));return o.a.createElement(o.a.Fragment,null,t)};var f=function(e){var t=0===e.selectedBucketId?"":e.todos.filter((function(t){return t.bucketId===e.selectedBucketId})).map((function(t){return o.a.createElement("div",{key:t.todoId,className:"todo-item"},o.a.createElement("div",{className:"tooltip"},o.a.createElement("input",{readOnly:!!t.todoStatus,style:t.todoStatus?{textDecoration:"line-through"}:{textDecoration:"none"},placeholder:"",onChange:function(a){return e.handleChangeToDoDesc(a,t.todoId)},value:t.todoDesc}),""===t.todoDesc.trim()?"":o.a.createElement("span",{class:"tooltiptext"},t.todoDesc)),o.a.createElement("button",{onClick:function(){return e.deleteThisTodo(t.todoId)}},"delete"),o.a.createElement("button",{onClick:function(){return e.toggleCompleteStatus(t.todoId)}},t.todoStatus?"mark incomplete":"mark complete"))}));return o.a.createElement(o.a.Fragment,null," ",t)},v=function(e){Object(m.a)(a,e);var t=Object(p.a)(a);function a(){var e;return Object(l.a)(this,a),(e=t.call(this)).scrollToBottom=function(){e.messagesEnd.scrollIntoView({behavior:"smooth"})},e.state={buckets:[{bucketId:0,name:""}],todos:[{bucketId:0,todoId:0,todoDesc:"",todoStatus:!1}],bucketId:0,todoId:0,currentBucketName:"",selectedBucketId:0},e.handleBucketNameChange=e.handleBucketNameChange.bind(Object(k.a)(e)),e.handleClickCreateNewBucket=e.handleClickCreateNewBucket.bind(Object(k.a)(e)),e.setCurrentBucketId=e.setCurrentBucketId.bind(Object(k.a)(e)),e.createNewTodo=e.createNewTodo.bind(Object(k.a)(e)),e.deleteThisTodo=e.deleteThisTodo.bind(Object(k.a)(e)),e.toggleCompleteStatus=e.toggleCompleteStatus.bind(Object(k.a)(e)),e.handleChangeToDoDesc=e.handleChangeToDoDesc.bind(Object(k.a)(e)),e}return Object(h.a)(a,[{key:"setCurrentBucketId",value:function(){var e=Object(i.a)(d.a.mark((function e(t){return d.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,this.setState({selectedBucketId:this.state.selectedBucketId===t?0:t});case 2:case"end":return e.stop()}}),e,this)})));return function(t){return e.apply(this,arguments)}}()},{key:"handleBucketNameChange",value:function(){var e=Object(i.a)(d.a.mark((function e(t){return d.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,this.setState({currentBucketName:t.target.value});case 2:case"end":return e.stop()}}),e,this)})));return function(t){return e.apply(this,arguments)}}()},{key:"handleClickCreateNewBucket",value:function(){var e=Object(i.a)(d.a.mark((function e(){var t,a,n;return d.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(""===this.state.currentBucketName){e.next=7;break}return t=this.state.bucketId,a=++t,n=this.state.currentBucketName,e.next=6,this.setState({buckets:[].concat(Object(s.a)(this.state.buckets),[{bucketId:a,name:n}]),currentBucketName:"",bucketId:a,selectedBucketId:a});case 6:this.bucketScrollView.scrollIntoView({behavior:"smooth"});case 7:case"end":return e.stop()}}),e,this)})));return function(){return e.apply(this,arguments)}}()},{key:"createNewTodo",value:function(){var e=Object(i.a)(d.a.mark((function e(){var t,a;return d.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return t=this.state.todoId,a=++t,e.next=4,this.setState({todos:[].concat(Object(s.a)(this.state.todos),[{bucketId:this.state.selectedBucketId,todoId:a,todoDesc:"",todoStatus:!1}]),todoId:a});case 4:this.todoScrollView.scrollIntoView({behavior:"smooth"});case 5:case"end":return e.stop()}}),e,this)})));return function(){return e.apply(this,arguments)}}()},{key:"deleteThisTodo",value:function(){var e=Object(i.a)(d.a.mark((function e(t){var a,n;return d.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return a=Object(s.a)(this.state.todos),n=a.findIndex((function(e){return e.todoId===t})),a.splice(n,1),e.next=5,this.setState({todos:a});case 5:case"end":return e.stop()}}),e,this)})));return function(t){return e.apply(this,arguments)}}()},{key:"toggleCompleteStatus",value:function(){var e=Object(i.a)(d.a.mark((function e(t){var a,n;return d.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return a=Object(s.a)(this.state.todos),n=a.map((function(e){return e.todoId===t?{bucketId:e.bucketId,todoId:e.todoId,todoDesc:e.todoDesc,todoStatus:!e.todoStatus}:e})),e.next=4,this.setState({todos:n});case 4:case"end":return e.stop()}}),e,this)})));return function(t){return e.apply(this,arguments)}}()},{key:"handleChangeToDoDesc",value:function(){var e=Object(i.a)(d.a.mark((function e(t,a){var n,o;return d.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return n=Object(s.a)(this.state.todos),o=n.map((function(e){return e.todoId===a?{bucketId:e.bucketId,todoId:e.todoId,todoDesc:t.target.value,todoStatus:e.todoStatus}:e})),e.next=4,this.setState({todos:o});case 4:case"end":return e.stop()}}),e,this)})));return function(t,a){return e.apply(this,arguments)}}()},{key:"render",value:function(){var e=this;return o.a.createElement(o.a.Fragment,null,o.a.createElement("div",{className:"App"},o.a.createElement("h1",null,o.a.createElement("p",null,"Todo App")),o.a.createElement("div",{className:"bucket"},o.a.createElement("div",{className:"bucket-create"},o.a.createElement("input",{onChange:function(t){e.handleBucketNameChange(t)},value:this.state.currentBucketName,placeholder:"Enter Bucket Name..."}),o.a.createElement("button",{onClick:this.handleClickCreateNewBucket},"Create Bucket")),o.a.createElement(b,{setCurrentBucketId:this.setCurrentBucketId,buckets:this.state.buckets,selectedBucketId:this.state.selectedBucketId}),o.a.createElement("div",{ref:function(t){e.bucketScrollView=t}})),o.a.createElement("div",{className:"todo"},o.a.createElement(f,{todos:this.state.todos,selectedBucketId:this.state.selectedBucketId,deleteThisTodo:this.deleteThisTodo,toggleCompleteStatus:this.toggleCompleteStatus,handleChangeToDoDesc:this.handleChangeToDoDesc}),0===this.state.selectedBucketId?o.a.createElement("p",{className:"getStarted"},"Please select/add some Buckets to get started"):o.a.createElement("div",{onClick:this.createNewTodo,className:"addnewtodo"},o.a.createElement("p",null,"Add New Task")),o.a.createElement("div",{ref:function(t){e.todoScrollView=t}}))))}}]),a}(o.a.Component);Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));r.a.render(o.a.createElement(o.a.StrictMode,null,o.a.createElement(v,null)),document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then((function(e){e.unregister()})).catch((function(e){console.error(e.message)}))}},[[11,1,2]]]);
//# sourceMappingURL=main.fef407bf.chunk.js.map