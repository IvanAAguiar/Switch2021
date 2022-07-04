import { Breadcrumb, Layout, Menu } from "antd";
import { useState } from "react";
import LoginForm from "../../components/LoginForm";
import RegisterForm from "../../components/RegisterForm";
import "./Login.css";
const { Header, Content, Footer } = Layout;

const menuItems = [
  {
    label: "home",
    key: "home",
  },
  {
    label: "faq",
    key: "faq",
  },
  {
    label: "contact",
    key: "contact",
  },
  {
    label: "register",
    key: "register",
  },
];

const Login = () => {
  const [navRegister, setNavRegister] = useState(false);

  function handleClick(e) {
    if (e.key === "register") setNavRegister(true);
    if (e.key === "home") setNavRegister(false);
  }

  const handleBreadcrumbClick = () => {
    window.location.reload(false);
  };

  return (
    <Layout className="layout">
      <Header>
        <div className="logoNav" />
        <Menu
          className="menuNav"
          theme="dark"
          mode="horizontal"
          defaultSelectedKeys={["1"]}
          items={menuItems}
          onClick={handleClick}
        />
      </Header>
      <Content
        style={{
          padding: "0 50px",
        }}
      >
        <Breadcrumb
          style={{
            margin: "16px 0",
          }}
        >
          <Breadcrumb.Item
            className="breadcrumb"
            onClick={handleBreadcrumbClick}
          >
            Home
          </Breadcrumb.Item>
          <Breadcrumb.Item>
            {navRegister ? "Register" : "Login"}
          </Breadcrumb.Item>
        </Breadcrumb>
        <div className="site-layout-content">
          {navRegister ? <RegisterForm /> : <LoginForm />}
        </div>
      </Content>
      <Footer
        style={{
          textAlign: "center",
        }}
      >
        BeaverApp ©2022 Created by Grupo 2
      </Footer>
    </Layout>
  );
};

export default Login;
