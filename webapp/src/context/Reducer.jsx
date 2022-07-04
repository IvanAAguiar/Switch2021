import {
  FETCH_COLLECTIONS_SUCCESS,
  FETCH_COLLECTIONS_FAILURE,
  FETCH_COLLECTIONS_STARTED,
  // FETCH_ADD_TO_COLLECTIONS_SUCCESS,
  // FETCH_ADD_TO_COLLECTIONS_FAILURE,
  FETCH_DETAILS_FAILURE,
  FETCH_DETAILS_STARTED,
  FETCH_DETAILS_SUCCESS,
  DELETE_DETAILS,
  FETCH_AUTH_FAILURE,
  FETCH_AUTH_SUCCESS,
  LOGOUT,
} from "./Actions";

export default function reducer(state, action) {
  // debugger
  switch (action.type) {
    case FETCH_COLLECTIONS_STARTED:
      return {
        ...state,
        collection: {
          loading: true,
          error: null,
          data: [],
        },
      };
    case FETCH_COLLECTIONS_SUCCESS:
      return {
        ...state,
        collection: {
          loading: false,
          error: null,
          data: [action.payload.data],
        },
      };
    case FETCH_COLLECTIONS_FAILURE:
      return {
        ...state,
        collection: {
          loading: false,
          error: action.payload.error,
          data: [],
        },
      };
    case FETCH_DETAILS_STARTED:
      return {
        ...state,
        details: {
          loading: true,
          error: null,
          data: [],
          userid: 0,
        },
      };
    case FETCH_DETAILS_SUCCESS:
      return {
        ...state,
        details: {
          loading: false,
          error: null,
          data: [action.payload.data],
          userid: state.details.userid,
        },
      };
    case FETCH_DETAILS_FAILURE:
      return {
        ...state,
        details: {
          loading: false,
          error: action.payload.error,
          data: [],
          userid: 0,
        },
      };
    case DELETE_DETAILS:
      return {
        ...state,
        details: {
          loading: false,
          error: null,
          data: [],
          userid: 0,
        },
      };
    case FETCH_AUTH_SUCCESS:
      return {
        ...state,

        auth: {
          loading: false,
          error: null,
          data: {
            token: action.payload.data.token,
            userName: action.payload.data.username,
            email: action.payload.data.email,
            links: action.payload.data._links,
          },
        },
      };
    case FETCH_AUTH_FAILURE:
      return {
        ...state,
        auth: {
          loading: false,
          error: action.payload.error,
          data: {
            token: "",
          },
        },
      };
    case LOGOUT:
      return {
        ...state,
        auth: {
          loading: false,
          error: null,
          data: {
            token: "",
          },
        },
      };
    default:
      return state;
  }
}
