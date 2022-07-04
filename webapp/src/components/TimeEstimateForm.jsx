import {
  Button,
  Col,
  Drawer,
  Form,
  Input,
  InputNumber,
  Row,
  Space,
  Spin,
} from "antd";
import { useContext, useState } from "react";
import { fetchDetails } from "../context/Actions";
import AppContext from "../context/AppContext";
import { URL_API } from "../services/Service";

const TimeEstimateForm = (props) => {
  const { dispatch } = useContext(AppContext);
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);

  const onClose = () => {
    props.onClose(false);
  };

  const onSubmit = (value) => {
    setLoading(true);

    const body = {
      priority: 0,
      timeEstimate: value.timeEstimate,
    };

    const url = `${URL_API}/userstories/${props.data.id}/effort`;

    const request = {
      method: "PATCH",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify(body),
    };

    fetchDetails(url, request, dispatch);

    props.onClose(false);
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

  return (
    <>
      <Drawer
        title="Define Time Estimate"
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
        <Form
          form={form}
          layout="vertical"
          onFinish={onSubmit}
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
            <Col span={24}>
              <Form.Item
                name="timeEstimate"
                label="Time Estimate"
                initialValue={props.data.timeEstimate}
                rules={[
                  { required: true, message: "Please enter time estimate" },
                ]}
              >
                <InputNumber min={0} addonAfter="hour" />
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

export default TimeEstimateForm;
