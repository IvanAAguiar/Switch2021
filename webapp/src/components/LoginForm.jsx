import { useContext, useRef } from "react";
import AppContext from "../context/AppContext";

import {
  Button,
  /*Checkbox,*/
  Col,
  Form,
  Input,
  Row,
  Select,
  Typography,
} from "antd";
import { URL_API } from "../services/Service";
import { authToAPI } from "../context/Actions";

const { Title } = Typography;
const { Option } = Select;

const LoginForm = () => {
  const { state, dispatch } = useContext(AppContext);
  const { auth } = state;
  const { loading, error, data } = auth;
  const form = useRef(null);

  const onFinish = (values) => {
    const url = URL_API;
    const request = {
      method: "POST",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify(values),
    };
    authToAPI(url, request, dispatch);
  };

  const onFinishFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
  };

  //Used to chose an profile to login
  const handleSelectorChange = (value) => {
    const password = "Qwerty1!";

    let email = "";

    switch (value) {
      case "Ze da Esquina":
        email = "zez@mymail.com";
        break;
      case "Joao Silva":
        email = "jsz@mymail.com";
        break;
      case "Tiago Cancado":
        email = "tcz@mymail.com";
        break;
      case "Urbino das Urzes":
        email = "udu@mymail.com";
        break;
      default:
        email = "";
    }

    form.current.setFieldsValue({
      email: email,
      password: password,
    });
  };

  const getError = () => {
    if (error !== null) {
      return (
        <Typography.Text
          style={{
            marginBottom: "10px",
            display: "flex",
            justifyContent: "center",
            color: "#f5222d",
          }}
        >
          {JSON.stringify(error)}
        </Typography.Text>
      );
    }
  };

  const getForm = () => {
    if (loading === true) {
      return <h1>Loading...</h1>;
    } else {
      if (JSON.stringify(data.token) !== "") {
        return (
          <Form
            name="basic"
            ref={form}
            labelCol={{
              span: 8,
            }}
            wrapperCol={{
              span: 16,
            }}
            initialValues={{
              remember: true,
            }}
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
            autoComplete="off"
          >
            <Form.Item
              label="Username"
              name="email"
              rules={[
                {
                  required: true,
                  message: "Please input your username!",
                },
              ]}
            >
              <Input style={{ width: "75%" }} />
            </Form.Item>

            <Form.Item
              label="Password"
              name="password"
              rules={[
                {
                  required: true,
                  message: "Please input your password!",
                },
              ]}
            >
              <Input.Password style={{ width: "75%" }} />
            </Form.Item>

            {/* <Form.Item
              name="remember"
              valuePropName="checked"
              wrapperCol={{
                offset: 8,
                span: 16,
              }}
            >
              <Checkbox>Remember me</Checkbox>
            </Form.Item> */}

            <Form.Item
              wrapperCol={{
                offset: 8,
                span: 16,
              }}
            >
              <Row gutter={24}>
                <Col>
                  <Button type="primary" htmlType="submit">
                    Login
                  </Button>
                </Col>
                <Col>
                  <Select
                    placeholder="Select a Profile"
                    style={{ width: 150 }}
                    onChange={handleSelectorChange}
                  >
                    <Option value="Ze da Esquina">Ze da Esquina</Option>
                    <Option value="Urbino das Urzes">Urbino das Urzes</Option>
                    <Option value="Tiago Cancado">Tiago Cancado</Option>
                    <Option value="Joao Silva">Joao Silva</Option>
                  </Select>
                </Col>
              </Row>
            </Form.Item>
          </Form>
        );
      } else {
        return (
          <Title
            level={3}
            style={{
              marginTop: "50px",
              display: "flex",
              justifyContent: "center",
              color: "#13c2c2",
            }}
          >
            Register successfully completed.
          </Title>
        );
      }
    }
  };

  return (
    <>
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          marginBottom: "10px",
        }}
      >
        <Title level={2} strong>
          Login
        </Title>
      </div>
      {getError()}
      {getForm()}
    </>
  );
};

export default LoginForm;
