import {
  Button,
  Drawer,
  Space,
  Transfer,
  Typography,
  Spin,
  Row,
  Col,
  Table,
} from "antd";
import { useState, useEffect } from "react";
import { makeHTTPRequest } from "../services/Service";
import { URL_API } from "../services/Service";

import difference from "lodash/difference";

const TableTransfer = ({ leftColumns, rightColumns, ...restProps }) => (
  <Transfer {...restProps}>
    {({
      direction,
      filteredItems,
      onItemSelectAll,
      onItemSelect,
      selectedKeys: listSelectedKeys,
      disabled: listDisabled,
    }) => {
      const columns = direction === "left" ? leftColumns : rightColumns;
      const rowSelection = {
        getCheckboxProps: (item) => ({
          disabled: listDisabled || item.disabled,
        }),

        onSelectAll(selected, selectedRows) {
          const treeSelectedKeys = selectedRows
            .filter((item) => !item.disabled)
            .map(({ key }) => key);
          const diffKeys = selected
            ? difference(treeSelectedKeys, listSelectedKeys)
            : difference(listSelectedKeys, treeSelectedKeys);
          onItemSelectAll(diffKeys, selected);
        },

        onSelect({ key }, selected) {
          onItemSelect(key, selected);
        },

        selectedRowKeys: listSelectedKeys,
      };
      return (
        <Table
          rowSelection={rowSelection}
          columns={columns}
          dataSource={filteredItems}
          size="small"
          style={{
            pointerEvents: listDisabled ? "none" : undefined,
          }}
          onRow={({ key, disabled: itemDisabled }) => ({
            onClick: () => {
              if (itemDisabled || listDisabled) return;
              onItemSelect(key, !listSelectedKeys.includes(key));
            },
          })}
        />
      );
    }}
  </Transfer>
);

const leftTableColumns = [
  {
    dataIndex: "title",
    title: "Name",
  },
  {
    dataIndex: "tag",
    title: "Priority",
  },
];
const rightTableColumns = [
  {
    dataIndex: "title",
    title: "Name",
  },
];

const AddUserStoryForm = (props) => {
  const [visible, setVisible] = useState(false);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const [targetKeys, setTargetKeys] = useState([]);
  const [mockData, setMockData] = useState([]);

  useEffect(() => {
    if (props.visible) {
      setVisible(true);
      setLoading(true);
    }

    const url = `${URL_API}/userstories/productBacklog/${props.project}`;

    const request = {};

    makeHTTPRequest(url, request, success, failure);
    // eslint-disable-next-line
  }, []);

  const success = (res) => {
    setLoading(false);

    if (Object.keys(res) !== 1) {
      const data = Object.values(res._embedded)[0].map((us, key) => {
        return {
          key: key,
          title: us.usTitle,
          tag: us.priority,
        };
      });

      setMockData(data);
    }
  };

  const failure = (err) => {
    setLoading(false);
    setError(err);
  };

  const actualUserStories =
    props.data._embedded === undefined
      ? Array.of(0)
      : props.data._embedded.userStoryOfSprintDTOList;

  useEffect(() => {
    const initialTargetKeys = mockData
      .filter(
        (item) =>
          actualUserStories.find((us) => {
            return us.usTitle === item.title;
          }) !== undefined
      )
      .map((item) => item.key);

    setTargetKeys(initialTargetKeys);
    // eslint-disable-next-line
  }, [mockData]);

  const onClose = () => {
    setVisible(false);
    props.onClose(false);
  };

  const onChange = (nextTargetKeys, direction, moveKeys) => {
    setTargetKeys(nextTargetKeys);
  };

  // const onSelectChange = (sourceSelectedKeys, targetSelectedKeys) => {
  //   setSelectedKeys([...sourceSelectedKeys, ...targetSelectedKeys]);
  // };

  const handleSubmit = () => {
    setLoading(true);
    if (targetKeys.length !== 0) {
      let userstories = targetKeys.map((key) => {
        return mockData[key].title;
      });

      let sum = 0;

      const successSubmit = () => {
        sum = sum + 1;
        if (sum === userstories.length - 1) {
          setLoading(false);
          setVisible(false);
          props.update(true);
          props.onClose(false);
        }
      };

      const failureSubmit = (err) => {
        setLoading(false);
        setError(err);
      };

      // eslint-disable-next-line
      userstories.map((userstory) => {
        const body = {
          projectID: props.project,
          title: userstory,
        };

        const url = `${URL_API}/sprints/${props.sprint}`;

        const request = {
          method: "POST",
          headers: {
            "content-Type": "application/json",
          },
          body: JSON.stringify(body),
        };

        makeHTTPRequest(url, request, successSubmit, failureSubmit);
      });
    }
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

  return (
    <>
      <Drawer
        title="Add User Story"
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
        <Row gutter={[0, 16]}>
          <Col span={24}>
            <TableTransfer
              dataSource={mockData}
              targetKeys={targetKeys}
              showSearch
              onChange={onChange}
              filterOption={(inputValue, item) =>
                item.title.indexOf(inputValue) !== -1 ||
                item.tag.indexOf(inputValue) !== -1
              }
              leftColumns={leftTableColumns}
              rightColumns={rightTableColumns}
            />
          </Col>
          <Col push={11} span={24}>
            <Button type="primary" onClick={handleSubmit}>
              Submit
            </Button>
          </Col>
        </Row>
      </Drawer>
    </>
  );
};

export default AddUserStoryForm;
