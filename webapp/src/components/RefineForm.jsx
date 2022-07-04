import {
  Button,
  Col,
  Drawer,
  Form,
  Input,
  InputNumber,
  Row,
  Space,
  Typography,
  Spin,
  Radio,
  Alert,
  Steps,
} from "antd";
import { useState } from "react";

import { makeHTTPRequest, URL_API } from "../services/Service";

const { Step } = Steps;

const RefineForm = (props) => {
  const [form] = Form.useForm();
  const [number, setNumber] = useState(0);
  const [newUS, setNewUS] = useState(0);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const onClose = () => {
    props.onClose(false);
  };

  const success = (res) => {
    setNewUS(newUS + 1);
    setLoading(false);
    form.resetFields();
    if (newUS === number - 1) {
      props.update(true);
      props.onClose(false);
    }
  };

  const failure = (err) => {
    setLoading(false);
    setError(err);
  };

  const onSubmit = (value) => {
    setLoading(true);

    const body = {
      projectID: props.data.projectID,
      title: value.title,
      priority: value.priority,
      description: value.description,
      timeEstimate: value.timeEstimate,
    };

    const url = `${URL_API}/userstories/${props.data.id}`;

    const request = {
      method: "PUT",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify(body),
    };

    makeHTTPRequest(url, request, success, failure);
  };

  const getError = () => {
    if (error !== "") {
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

  const getLoading = () => {
    if (loading === true) {
      return (
        <div className="spin-loading">
          <Spin />
        </div>
      );
    }
  };

  const getProgress = () => {
    if (number !== 0) {
      const steps = [];
      for (let index = 0; index < number; index++) {
        steps.push(index + 1);
      }
      return (
        <>
          <Steps size="small" current={newUS}>
            {steps.map((value, idx) => {
              return <Step key={idx} title={`New User Story ${value}`} />;
            })}
          </Steps>
          <br />
        </>
      );
    }
  };

  const getNumberOfUserStories = () => {
    if (number === 0) {
      return (
        <Form
          layout="vertical"
          onFinish={(value) => setNumber(value.number)}
          autoComplete="off"
          scrollToFirstError
        >
          <Row gutter={16}>
            <Col span={24}>
              <Form.Item
                name="project"
                label="Project"
                initialValue={props.data.projectID}
              >
                <Input disabled />
              </Form.Item>
            </Col>
            <Col span={24}>
              <Form.Item
                name="userStory"
                label="User Story"
                initialValue={props.data.usTitle}
              >
                <Input disabled />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}>
            <Col span={24}></Col>
          </Row>
          <Row>
            <Col span={24}>
              <Form.Item
                name="number"
                label="How many new user stories will be created"
                initialValue={0}
                rules={[
                  {
                    required: true,
                    message: "Please enter the number of new user stories",
                  },
                ]}
              >
                <InputNumber min={0} />
              </Form.Item>
              <Alert
                message="Warning: This User Story will be refined into more user stories and will be blocked"
                type="warning"
                closable
              />
              <br />
            </Col>
          </Row>
          <Row>
            <Col span={24}>
              <Form.Item
                wrapperCol={{
                  offset: 11,
                  span: 16,
                }}
              >
                <Button type="primary" htmlType="submit">
                  Submit
                </Button>
              </Form.Item>
            </Col>
          </Row>
        </Form>
      );
    }
  };

  return (
    <>
      <Drawer
        title="Refine User Story"
        width={720}
        onClose={onClose}
        visible="true"
        bodyStyle={{
          paddingBottom: 80,
        }}
        extra={
          <Space>
            <Button onClick={onClose}>Cancel</Button>
          </Space>
        }
      >
        {getLoading()}
        {getError()}
        {getNumberOfUserStories()}
        {getProgress()}
        <Form
          form={form}
          layout="vertical"
          onFinish={onSubmit}
          autoComplete="off"
          scrollToFirstError
          hidden={number === 0}
        >
          <Row gutter={16}>
            <Col span={24}>
              <Form.Item
                name="project"
                label="Project"
                initialValue={props.data.projectID}
              >
                <Input disabled />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}>
            <Col span={24}>
              <Alert
                message="Warning: User Story title must have a specific format: 'As _____ want _____'"
                type="warning"
                closable
              />
              <br />
            </Col>
          </Row>
          <Row>
            <Col span={24}>
              <Form.Item
                name="title"
                label="Title"
                rules={[
                  {
                    required: true,
                    message: "Please enter user story title",
                  },
                ]}
              >
                <Input placeholder="As user I want to create a new user story" />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}>
            <Col span={24}>
              <Form.Item
                name="description"
                label="Description"
                rules={[
                  { required: true, message: "Please enter description" },
                ]}
              >
                <Input.TextArea rows={1} />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="timeEstimate"
                label="Time Estimate"
                rules={[
                  { required: true, message: "Please enter time estimate" },
                ]}
              >
                <InputNumber min={0} addonAfter="hour" />
              </Form.Item>
            </Col>
            <Col span={24}>
              <Form.Item
                name="priority"
                label="Priority"
                rules={[{ required: true, message: "Please enter priority" }]}
              >
                <Radio.Group>
                  <Radio value={1}>1 (Higher)</Radio>
                  <Radio value={2}>2</Radio>
                  <Radio value={3}>3</Radio>
                  <Radio value={4}>4</Radio>
                  <Radio value={5}>5 (Lower)</Radio>
                </Radio.Group>
              </Form.Item>
            </Col>
          </Row>
          <Row>
            <Col span={24}>
              <Form.Item
                wrapperCol={{
                  offset: 11,
                  span: 16,
                }}
              >
                <Button type="primary" htmlType="submit">
                  Submit
                </Button>
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Drawer>
    </>
  );
};

export default RefineForm;
