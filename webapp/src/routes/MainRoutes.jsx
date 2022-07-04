import React, { useContext, useState } from "react";
import { Route, Routes, useLocation, useNavigate } from "react-router-dom";

import {
  UserOutlined,
  IdcardOutlined,
  MoneyCollectOutlined,
  HomeOutlined,
  ProjectOutlined,
  TeamOutlined,
  LogoutOutlined,
} from "@ant-design/icons";
import { Breadcrumb, Layout, Menu, PageHeader } from "antd";

import AppContext from "../context/AppContext";
import { logout, resetCollection } from "../context/Actions";
import Home from "../pages/Home";
import Login from "../pages/Login/Login";
import Projects from "../pages/Projects";
import Users from "../pages/Users";
import Typologies from "../pages/Typologies";
import Profiles from "../pages/Profiles";
import UserDetails from "../pages/UserDetails";
import MyInfo from "../pages/MyInfo";
import ProjectDetails from "../pages/ProjectDetails";
import MyProjects from "../pages/MyProjects";
import ProjectTeam from "../pages/ProjectTeam";
import ResourceDetails from "../pages/ResourceDetails";
import ProductBacklog from "../pages/ProductBacklog";
import UserStoryDetails from "../pages/UserStoryDetails";
import Sprints from "../pages/Sprints";
import SprintDetails from "../pages/SprintDetails";
import ScrumBoard from "../pages/ScrumBoard";
import Tasks from "../pages/Tasks";
import Categories from "../pages/Categories";

const { Header, Content, Footer, Sider } = Layout;

function getItem(label, key, icon, children) {
  return {
    key,
    icon,
    children,
    label,
  };
}

const itemsBottom = [getItem("Logout", "logout", <LogoutOutlined />)];

const getDate = () => {
  const today = new Date();

  return (
    "Hello, " +
    today.getFullYear() +
    "-" +
    (today.getMonth() + 1) +
    "-" +
    today.getDate() +
    " " +
    today.getHours() +
    ":" +
    today.getMinutes()
  );
};

function MainRoute() {
  const { state, dispatch } = useContext(AppContext);
  const { auth } = state;
  const { data } = auth;
  // const { links } = data;

  const [collapsed, setCollapsed] = useState(false);
  const navigate = useNavigate();
  const location = useLocation();

  const handleMenuClick = (e) => {
    if (e.key === "logout") {
      logout(dispatch);
      navigate("/");
    } else {
      if (location.pathname.replace("/", "") !== e.key) {
        resetCollection(dispatch);

        navigate(e.key, { state: { email: data.email }, replace: true });
      }
    }
  };

  if (data.token === "") {
    return (
      <Routes>
        <Route path="/" element={<Login />} />
      </Routes>
    );
  } else {
    // const alloweds = Object.keys(links);

    const itemsTop = [
      getItem("Home", "/", <HomeOutlined />),
      getItem("My Projects", "myprojects", <ProjectOutlined />),
    ];

    // if (alloweds.includes("projects")) {
    itemsTop.push(getItem("Projects", "projects", <ProjectOutlined />));
    // }

    // if (alloweds.includes("users")) {
    itemsTop.push(getItem("Users", "users", <TeamOutlined />));
    // }

    // if (alloweds.includes("typologies")) {
    itemsTop.push(
      getItem("Typologies", "typologies", <MoneyCollectOutlined />)
    );
    // }

    // if (alloweds.includes("profiles")) {
    itemsTop.push(getItem("Profiles", "profiles", <IdcardOutlined />));
    // }

    itemsTop.push(getItem("My Info", "myinfo", <UserOutlined />));

    return (
      <Layout
        style={{
          minHeight: "100vh",
        }}
      >
        <Sider
          collapsible
          collapsed={collapsed}
          onCollapse={(value) => {
            setCollapsed(value);
          }}
        >
          <div className="logo" />
          <Menu
            theme="dark"
            defaultSelectedKeys={["1"]}
            mode="inline"
            items={itemsTop}
            onClick={handleMenuClick}
          />
          <Menu
            theme="dark"
            defaultSelectedKeys={["1"]}
            mode="inline"
            items={itemsBottom}
            onClick={handleMenuClick}
            style={{
              position: "absolute",
              bottom: "48px",
            }}
          />
        </Sider>
        <Layout className="site-layout">
          <Header
            className="site-layout-background"
            style={{
              padding: 0,
            }}
          >
            <PageHeader>{getDate()}</PageHeader>
          </Header>
          <Content
            style={{
              margin: "0 16px",
            }}
          >
            <Breadcrumb
              style={{
                margin: "16px 0",
              }}
            >
              <Breadcrumb.Item>User</Breadcrumb.Item>
              <Breadcrumb.Item>{data.userName}</Breadcrumb.Item>
            </Breadcrumb>
            <div
              className="site-layout-background"
              style={{
                padding: 24,
                minHeight: 360,
              }}
            >
              <Routes>
                <Route path="/" element={<Home />} />
                <Route path="projects" element={<Projects />} />
                <Route path="myprojects" element={<MyProjects />} />
                <Route path="users" element={<Users />} />
                <Route path="typologies" element={<Typologies />} />
                <Route path="profiles" element={<Profiles />} />
                <Route path="myinfo" element={<MyInfo />} />
                <Route path="users/userdetails" element={<UserDetails />} />
                <Route
                  path="projects/projectdetails"
                  element={<ProjectDetails />}
                />
                <Route
                  path="projects/projectdetails/projectteam"
                  element={<ProjectTeam />}
                />
                <Route
                  path="projects/projectdetails/projectteam/resourcedetails"
                  element={<ResourceDetails />}
                />
                <Route
                  path="projects/projectdetails/productbacklog"
                  element={<ProductBacklog />}
                />
                <Route
                  path="projects/projectdetails/productbacklog/userstorydetails"
                  element={<UserStoryDetails />}
                />
                <Route
                  path="projects/projectdetails/sprints"
                  element={<Sprints />}
                />
                <Route
                  path="projects/projectdetails/sprints/sprintdetails"
                  element={<SprintDetails />}
                />
                <Route
                  path="projects/projectdetails/sprints/sprintdetails/scrumboard"
                  element={<ScrumBoard />}
                />
                <Route
                  path="projects/projectdetails/sprints/sprintdetails/scrumboard/categories"
                  element={<Categories />}
                />
                <Route
                  path="projects/projectdetails/sprints/sprintdetails/tasks"
                  element={<Tasks />}
                />
              </Routes>
            </div>
          </Content>
          <Footer
            style={{
              textAlign: "center",
            }}
          >
            BeaverAPP ©2022 Created by Grupo 2
          </Footer>
        </Layout>
      </Layout>
    );
  }
}

export default MainRoute;
