import { useContext, useEffect, useState } from "react";
import { URL_API } from "../services/Service";
import AppContext from "../context/AppContext";
import { fetchCollections, resetCollection } from "../context/Actions";

import { Col, Space, Row, Spin, Button } from "antd";
import Search from "antd/lib/input/Search";
import { PlusOutlined } from "@ant-design/icons";

import TableCollections from "../components/TableCollections";
import CreateTypologyForm from "../components/CreateTypologyForm";

export default function Typologies() {
  const { state, dispatch } = useContext(AppContext);
  const { collection } = state;
  const { loading, error, data } = collection;

  const [search, setSearch] = useState(false);
  const [textSearch, setTextSearch] = useState("");
  const [TypologyForm, setTypologyForm] = useState(false);

  const handleButtonClickDelete = () => {
    console.log("Deleted");
  };

  const columns = [
    {
      title: "ID",
      dataIndex: "key",

      sorter: (a, b) => ("" + a.key).localeCompare(b.key),
    },
    {
      title: "Name",
      dataIndex: "name",
      sorter: (a, b) => ("" + a.name).localeCompare(b.name),
    },
    {
      title: "Action",
      key: "action",
      render: (_, record) => (
        <Space size="middle">
          <button
            className="anchor-button"
            onClick={() => handleButtonClickDelete(record)}
          >
            Delete
          </button>
        </Space>
      ),
    },
  ];

  useEffect(() => {
    resetCollection(dispatch);
    let url = `${URL_API}/typologies`;

    const request = {};
    fetchCollections(url, request, dispatch);
    // eslint-disable-next-line
  }, [TypologyForm]);

  const onSearch = (value) => {
    setSearch(true);
    setTextSearch(value);
  };

  const searchTypologies = () => {
    return (
      <Search
        style={{
          width: 300,
          marginBottom: 10,
        }}
        placeholder="Search for Typology Here"
        onSearch={onSearch}
        enterButton
      />
    );
  };

  const createTypology = () => {
    setTypologyForm(true);
  };

  const getCreateTypologyForm = () => {
    if (TypologyForm) {
      return (
        <CreateTypologyForm visible={TypologyForm} onClose={setTypologyForm} />
      );
    }
  };

  if (loading === true) {
    return (
      <div className="spin-loading">
        <Spin />
      </div>
    );
  } else {
    if (error !== null) {
      return <h1 style={{ color: "red" }}>{error}</h1>;
    } else {
      if (Object.keys(data[0])[0] === "_embedded") {
        const collect = Object.keys(data[0]._embedded)[0];

        let response = data[0]._embedded[collect].map((typology, idx) => {
          return { key: idx, name: typology.description };
        });

        if (search) {
          response = response.filter((data) => {
            const toCheckName = JSON.stringify(data.name).toLowerCase().trim();

            const searchTypologies = textSearch.toLowerCase().trim();
            return toCheckName.includes(searchTypologies);
          });
        }

        return (
          <>
            {getCreateTypologyForm()}
            <Row gutter={16}>
              <Col span={2}>
                <Button
                  type="primary"
                  onClick={createTypology}
                  icon={<PlusOutlined />}
                >
                  Create
                </Button>
              </Col>
              <Col span={10}>{searchTypologies()}</Col>
            </Row>
            <Row gutter={16}>
              <Col span={24}>
                <TableCollections
                  title="Internal"
                  data={response}
                  header={columns}
                />
              </Col>
            </Row>
          </>
        );
      }
    }
  }
}
