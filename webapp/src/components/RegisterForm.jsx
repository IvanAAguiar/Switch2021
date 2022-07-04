import { useContext } from "react";
import AppContext from "../context/AppContext";
import { fetchAddToCollections } from "../context/Actions";

import { Button, Form, Input, Typography } from "antd";
// import UploadBox from "./UploadBox";
import { URL_API } from "../services/Service";

const { Title } = Typography;

const LoginForm = () => {
  const { state, dispatch } = useContext(AppContext);
  const { details } = state;
  const { loading, error, data } = details;

  const onFinish = (values) => {
    const url = `${URL_API}/users`;

    const postRequest = {
      method: "POST",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify(values),
    };

    fetchAddToCollections(url, postRequest, dispatch);
  };

  const onFinishFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
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
              label="Full Name"
              name="userName"
              rules={[
                {
                  required: true,
                  message: "Please input your full name!",
                },
              ]}
            >
              <Input style={{ width: "75%" }} />
            </Form.Item>
            <Form.Item
              label="E-mail"
              name="email"
              rules={[
                {
                  required: true,
                  type: "email",
                  message: "Please input your e-mail!",
                },
              ]}
            >
              <Input style={{ width: "75%" }} />
            </Form.Item>
            <Form.Item
              label="Function"
              name="function"
              rules={[
                {
                  required: true,
                  message: "Please input your function!",
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
            <Form.Item
              label="Confirm Password"
              name="passwordConfirmation"
              rules={[
                {
                  required: true,
                  message: "Please input your confirmation of password!",
                },
              ]}
            >
              <Input.Password style={{ width: "75%" }} />
            </Form.Item>

            <Form.Item label="Photo" name="photo" initialValue="" hidden>
              <Input />
              {/* <UploadBox /> */}
            </Form.Item>

            <Form.Item
              wrapperCol={{
                offset: 8,
                span: 16,
              }}
            >
              <Button type="primary" htmlType="submit">
                Register
              </Button>
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
          Register
        </Title>
      </div>
      {getError()}
      {getForm()}
    </>
  );
};

export default LoginForm;
