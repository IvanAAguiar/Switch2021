import {
  Button,
  Col,
  DatePicker,
  Drawer,
  Form,
  Input,
  InputNumber,
  Row,
  Select,
  Space,
  Typography,
  Spin,
} from "antd";
import { useContext, useEffect, useState } from "react";

import { makeHTTPRequest, URL_API } from "../services/Service";
import { fetchDetails } from "../context/Actions";
import AppContext from "../context/AppContext";
import moment from "moment";

const { Option } = Select;

const dateFormat = "YYYY-MM-DD";

const CreateProjectForm = (props) => {
  const { dispatch } = useContext(AppContext);
  const [visible, setVisible] = useState(false);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const [data, setData] = useState([]);

  useEffect(() => {
    if (props.visible) {
      setVisible(true);
      setLoading(true);
    }

    const urlTypologies = `${URL_API}/typologies`;

    const request = {};

    makeHTTPRequest(urlTypologies, request, success, failure);

    // eslint-disable-next-line
  }, []);

  const success = (res) => {
    setLoading(false);

    const typologies = res._embedded.Typologies.map((typo, idx) => {
      return (
        <Option key={idx} value={typo.description}>
          {typo.description}
        </Option>
      );
    });

    setData(typologies);
  };

  const failure = (err) => {
    setLoading(false);
    setError(err);
  };

  const onClose = () => {
    setVisible(false);
    props.onClose(false);
  };

  const onSubmit = (value) => {
    const body = {
      code: "",
      projectName: value.projectName,
      description: value.description,
      businessSector: value.businessSector,
      typology: value.typology,
      customer: value.customer,
      startDate: moment(value.startDate).format(dateFormat),
      endDate: moment(value.endDate).format(dateFormat),
      numberOfSprints: value.numberOfSprints.toString(),
      budget: value.budget.toString(),
      projectStatus: "",
      sprintDuration: value.sprintDuration,
    };

    const url = `${URL_API}/projects`;

    const putRequest = {
      method: "POST",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify(body),
    };

    fetchDetails(url, putRequest, dispatch);
    setVisible(false);
    props.onClose(false);
  };

  const getError = () => {
    if (error !== null) {
     return ( <Typography.Text
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
      return (<div className="spin-loading">
        <Spin />
      </div>);
    }
  };

  return (
    <>
      <Drawer
        title="Create new project"
        width={720}
        onClose={onClose}
        visible={visible}
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
        <Form
          layout="vertical"
          onFinish={onSubmit}
          autoComplete="off"
          scrollToFirstError
        >
          <Row gutter={16}>
            <Col span={24}>
              <Form.Item
                name="projectName"
                label="Name"
                rules={[{ required: true, message: "Please enter name" }]}
              >
                <Input />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}>
            <Col span={24}>
              <Form.Item
                name="description"
                label="Description"
                rules={[
                  { required: true, message: "Please enter name" },
                  {
                    min: 1,
                    message: "At least two characters.",
                  },
                  {
                    max: 200,
                    message: "Maximum of two hundred characters.",
                  },
                ]}
              >
                <Input.TextArea rows={1} />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="customer"
                label="Customer"
                rules={[{ required: true, message: "Please enter name" }]}
              >
                <Input />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="businessSector"
                label="Business Sector"
                rules={[{ required: true, message: "Please enter name" }]}
              >
                <Input />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="typology"
                label="Typology"
                rules={[{ required: true, message: "Please enter name" }]}
              >
                <Select>{data}</Select>
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="budget"
                label="Budget"
                rules={[{ required: true, message: "Please enter name" }]}
              >
                <InputNumber addonAfter="€" />
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item
                name="startDate"
                label="Start Date"
                rules={[{ required: true, message: "Please enter name" }]}
              >
                <DatePicker format={dateFormat} />
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item name="endDate" label="End Date">
                <DatePicker format={dateFormat} />
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item
                name="projectStatus"
                label="Status"
                initialValue="PLANNED"
              >
                <Input disabled />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="numberOfSprints"
                label="Number of Sprints"
                rules={[{ required: true, message: "Please enter name" }]}
              >
                <InputNumber controls={true} />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="sprintDuration"
                label="Sprint Duration"
                rules={[{ required: true, message: "Please enter name" }]}
              >
                <Select>
                  <Option value="7">7 (one week)</Option>
                  <Option value="14">14 (two weeks)</Option>
                  <Option value="21">21 (three weeks)</Option>
                  <Option value="28">28 (four weeks)</Option>
                </Select>
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

export default CreateProjectForm;
