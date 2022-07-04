import { Row, Typography, Col, Button } from "antd";
import React, { useContext, useEffect, useState } from "react";
import { DragDropContext, Draggable, Droppable } from "react-beautiful-dnd";
import { useLocation, useNavigate } from "react-router-dom";
import uuid from "uuid/v4";
import { CaretLeftOutlined } from "@ant-design/icons";
import { resetCollection } from "../context/Actions";
import AppContext from "../context/AppContext";
import { makeHTTPRequest, URL_API } from "../services/Service";

function Categories() {
  const { dispatch } = useContext(AppContext);
  const [columns, setColumns] = useState({});
  const navigate = useNavigate();
  const location = useLocation();

  const columnsFromBackend = {
    [uuid()]: {
      name: "To do",
      items: [],
    },
    [uuid()]: {
      name: "In Progress",
      items: [],
    },
    [uuid()]: {
      name: "Done",
      items: [],
    },
    [uuid()]: {
      name: "Cancelled",
      items: [],
    },
  };

  useEffect(() => {
    setColumns(columnsFromBackend);

    if (Object.keys(location.state.data).length !== 0) {
      // eslint-disable-next-line
      itemsFromBackend.map((us) => {
        Object.values(columnsFromBackend)
            .find(
                (category) => category.name.replace(/\s/g, "") === us.content.status
            )
            .items.push({id: us.id, content: us.content.usTitle});
      });
      setColumns(columnsFromBackend);
    }
    // eslint-disable-next-line
  }, []);

  const itemsFromBackend =
    location.state.data._embedded !== undefined
      ? location.state.data._embedded.userStoryOfSprintDTOList.map((us) => {
          return { id: uuid(), content: us };
        })
      : Array.of(0);

  const onDragEnd = (result, columns, setColumns) => {
    if (!result.destination) return;
    const { source, destination } = result;

    if (source.droppableId !== destination.droppableId) {
      const sourceColumn = columns[source.droppableId];
      const destColumn = columns[destination.droppableId];
      const sourceItems = [...sourceColumn.items];
      const destItems = [...destColumn.items];
      const [removed] = sourceItems.splice(source.index, 1);
      destItems.splice(destination.index, 0, removed);
      setColumns({
        ...columns,
        [source.droppableId]: {
          ...sourceColumn,
          items: sourceItems,
        },
        [destination.droppableId]: {
          ...destColumn,
          items: destItems,
        },
      });
    } else {
      const column = columns[source.droppableId];
      const copiedItems = [...column.items];
      const [removed] = copiedItems.splice(source.index, 1);
      copiedItems.splice(destination.index, 0, removed);
      setColumns({
        ...columns,
        [source.droppableId]: {
          ...column,
          items: copiedItems,
        },
      });
    }
  };

  const handleClick = () => {
    const userstories = location.state.data._embedded.userStoryOfSprintDTOList;
    const categories = Object.values(columns);

    // eslint-disable-next-line
    categories.map((category) => {
      // eslint-disable-next-line
      category.items.map((newUs) => {
        const foundUs = userstories.find((us) => newUs.content === us.usTitle);

        if (foundUs.status !== category.name.replace(/\s/g, "")) {
          const url = `${URL_API}/sprints/scrumBoard/${
            foundUs.projectId + "&" + foundUs.sprintName
          }`;

          const body = {
            usTitle: foundUs.usTitle,
            projectId: foundUs.projectId,
            sprintName: foundUs.sprintName,
            status: category.name.replace(/\s/g, ""),
          };

          const request = {
            method: "PATCH",
            headers: {
              "content-Type": "application/json",
            },
            body: JSON.stringify(body),
          };

          const success = () => {
            console.log("success");
          };

          const failure = () => {
            console.log("failure");
          };

          makeHTTPRequest(url, request, success, failure);
        }
      });
    });

    resetCollection(dispatch);
    navigate("../projects/projectDetails/sprints/sprintdetails/scrumboard", {
      state: { sprint: location.state.sprint, project: location.state.project },
    });
  };

  const back = () => {
    navigate("../projects/projectDetails/sprints/sprintdetails/scrumboard", {
      state: { sprint: location.state.sprint, project: location.state.project },
    });
  };

  return (
    <>
      <Row align="middle" style={{ paddingBottom: 20 }} gutter={16}>
        <Col>
          <Button
            ghost
            type="primary"
            onClick={back}
            icon={<CaretLeftOutlined />}
          >
            Back
          </Button>
        </Col>
        <Col>
          <Button type="primary" onClick={handleClick}>
            Submit
          </Button>
        </Col>
        <Col>
          <Typography.Text style={{ fontSize: 30, fontWeight: 700 }}>
            Scrum Board Categories
          </Typography.Text>
        </Col>
      </Row>
      <Row>
        <Col span={24}>
          <div
            style={{
              display: "flex",
              justifyContent: "center",
              height: "100%",
            }}
          >
            <DragDropContext
              onDragEnd={(result) => onDragEnd(result, columns, setColumns)}
            >
              {Object.entries(columns).map(([columnId, column], index) => {
                return (
                  <div
                    style={{
                      display: "flex",
                      flexDirection: "column",
                      alignItems: "center",
                    }}
                    key={columnId}
                  >
                    <h2>{column.name}</h2>
                    <div style={{ margin: 8 }}>
                      <Droppable droppableId={columnId} key={columnId}>
                        {(provided, snapshot) => {
                          return (
                            <div
                              {...provided.droppableProps}
                              ref={provided.innerRef}
                              style={{
                                background: snapshot.isDraggingOver
                                  ? "lightblue"
                                  : "lightgrey",
                                padding: 4,
                                width: 250,
                                minHeight: 500,
                              }}
                            >
                              {column.items.map((item, index) => {
                                return (
                                  <Draggable
                                    key={item.id}
                                    draggableId={item.id}
                                    index={index}
                                  >
                                    {(provided, snapshot) => {
                                      return (
                                        <div
                                          ref={provided.innerRef}
                                          {...provided.draggableProps}
                                          {...provided.dragHandleProps}
                                          style={{
                                            userSelect: "none",
                                            padding: 8,
                                            margin: "0 0 8px 0",
                                            minHeight: "35px",
                                            backgroundColor: snapshot.isDragging
                                              ? "#002329"
                                              : "#00474f",
                                            color: "white",
                                            ...provided.draggableProps.style,
                                          }}
                                        >
                                          {item.content}
                                        </div>
                                      );
                                    }}
                                  </Draggable>
                                );
                              })}
                              {provided.placeholder}
                            </div>
                          );
                        }}
                      </Droppable>
                    </div>
                  </div>
                );
              })}
            </DragDropContext>
          </div>
        </Col>
      </Row>
    </>
  );
}

export default Categories;
