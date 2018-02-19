**Node.js**

1. Node.js is a **JavaScript runtime** built on Chrome's V8 JavaScript engine. Written in C++.

2. Advantage: **event-driven, non-blocking I/O model** that makes it lightweight and efficient.

3. npm, is Node.js' package ecosystem, one of the largest open source libraries in the world.

4. Currently, the popular backend language: PHP, Java, .Net. Using backend language to develop on server is called backend technique. **Node.js is a tool that allow JavaScript to develop on server.** That means, node.js can make our server to run JavaScript code, so many frontend engineers can write backend code in JavaScript.

   JavaScript(Frontend language) + Node.js(JavaScript runtime) = Development on server(Backend technique)

5. Paypal gradually moves from Java to Node.js since 2013, the performance has imporved one time, and fewer lines of code, fewer files constructed. [https://www.paypal-engineering.com/2013/11/22/node-js-at-paypal/]

6. Node.js is not suitable for heavy CPU apps, e.g. computation, video and picture execution related to Artificial Intelligence. 

   > Node js, despite its asynchronous event model, by nature single threaded. When you launch a Node process, you are running a single process with a single thread on a single core. So your *code *will not be executed in parallel, only I/O operations are parallel because they are executed asynchronous. As such, long running CPU tasks will block the whole server and are usually a bad idea.

7. In our web dev course, npm is acting like a open source libraries, to add our dependencies in Phoenix.