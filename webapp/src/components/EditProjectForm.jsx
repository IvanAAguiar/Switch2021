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
} from "antd";
import { useContext, useEffect, useState } from "react";

import { URL_API } from "../services/Service";
import { fetchDetails } from "../context/Actions";
import AppContext from "../context/AppContext";
import moment from "moment";

const { Option } = Select;

const dateFormat = "YYYY-MM-DD";

const EditProjectForm = (props) => {
  const { dispatch } = useContext(AppContext);
  const [visible, setVisible] = useState(false);

  useEffect(() => {
    if (props.visible) {
      setVisible(true);
    }
    // eslint-disable-next-line
  }, []);

  const onClose = () => {
    setVisible(false);
    props.onClose(false);
  };

  const onSubmit = (value) => {
    const body = {
      code: value.code,
      projectName: value.projectName,
      description: value.description,
      businessSector: value.businessSector,
      typology: value.typology,
      customer: value.customer,
      startDate: moment(value.startDate).format(dateFormat),
      endDate: moment(value.endDate).format(dateFormat),
      numberOfSprints: value.numberOfSprints.toString(),
      budget: value.budget.toString(),
      projectStatus: value.projectStatus,
      sprintDuration: value.sprintDuration,
    };

    const url = `${URL_API}/projects/${props.data.code}`;

    const putRequest = {
      method: "PATCH",
      headers: {
        "content-Type": "application/json",
      },
      body: JSON.stringify(body),
    };

    fetchDetails(url, putRequest, dispatch);
    setVisible(false);
    props.onClose(false);
  };

  const budget = Number(props.data.budget.replace(",", "."));

  const typeStatus = [
    "PLANNED",
    "INCEPTION",
    "ELABORATION",
    "CONSTRUCTION",
    "TRANSITION",
    "WARRANTY",
    "CLOSED",
  ];

  const statusOptions = () =>
    typeStatus.map((status, idx) => {
      return (
        <Option key={idx} value={status}>
          {status}
        </Option>
      );
    });

  return (
    <>
      <Drawer
        title="Edit project"
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
        <Form
          layout="vertical"
          onFinish={onSubmit}
          autoComplete="off"
          scrollToFirstError
          hideRequiredMark
        >
          <Row gutter={16}>
            <Col span={12}>
              <Form.Item
                name="code"
                label="Code"
                initialValue={props.data.code}
              >
                <Input disabled />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="projectName"
                label="Name"
                initialValue={props.data.projectName}
              >
                <Input disabled />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={16}>
            <Col span={24}>
              <Form.Item
                name="description"
                label="Description"
                initialValue={props.data.description}
                rules={[
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
                initialValue={props.data.customer}
              >
                <Input disabled />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="businessSector"
                label="Business Sector"
                initialValue={props.data.businessSector}
              >
                <Input disabled />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="typology"
                label="Typology"
                initialValue={props.data.typo}
              >
                <Input disabled />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item name="budget" label="Budget" initialValue={budget}>
                <InputNumber addonAfter="€" disabled />
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item
                name="startDate"
                label="Start Date"
                initialValue={moment(props.data.startDate, dateFormat)}
                rules={[]}
              >
                <DatePicker format={dateFormat} />
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item
                name="endDate"
                label="End Date"
                initialValue={moment(props.data.endDate, dateFormat)}
                rules={[]}
              >
                <DatePicker format={dateFormat} />
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item
                name="projectStatus"
                label="Status"
                initialValue={props.data.status}
              >
                <Select>{statusOptions()}</Select>
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="numberOfSprints"
                label="Number of Sprints"
                initialValue={props.data.numberOfSprints}
                rules={[]}
              >
                <InputNumber controls={true} />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item
                name="sprintDuration"
                label="Sprint Duration"
                initialValue={props.data.sprintDuration}
                rules={[]}
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

export default EditProjectForm;
