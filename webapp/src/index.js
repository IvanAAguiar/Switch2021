import React from "react";
import ReactDOM from "react-dom/client";
import "antd/dist/antd.min.css";

import App from "./App";
import { ConfigProvider } from "antd";
import { BrowserRouter } from "react-router-dom";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <BrowserRouter>
    {/* <React.StrictMode> */}
    <ConfigProvider>
      <App />
    </ConfigProvider>
    {/* </React.StrictMode> */}
  </BrowserRouter>
);
