import { Table } from "antd";

const onChange = (pagination, filters, sorter, extra) => {
  console.log("params", pagination, filters, sorter, extra);
};

const TableCollections = (props) => (
  <Table
    title={() => <h1>{props.title}</h1>}
    size="middle"
    columns={props.header}
    dataSource={props.data}
    onChange={onChange}
  />
);

export default TableCollections;
