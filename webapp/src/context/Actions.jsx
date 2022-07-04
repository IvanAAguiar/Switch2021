import { makeHTTPRequest } from "../services/Service";

// ACTIONS TO LOGIN/LOGOUT

export const FETCH_AUTH_SUCCESS = "FETCH_AUTH_SUCCESS";
export const FETCH_AUTH_FAILURE = "FETCH_AUTH_FAILURE";
export const LOGOUT = "LOGOUT";

export function authToAPI(url, request, dispatch) {
  const success = (res) => dispatch(fetchAuthSuccess(res));
  const failure = (err) => dispatch(fetchAuthFailure(err));
  makeHTTPRequest(url, request, success, failure);
}

function fetchAuthSuccess(res) {
  return {
    type: FETCH_AUTH_SUCCESS,
    payload: {
      data: { ...res },
    },
  };
}

function fetchAuthFailure(err) {
  return {
    type: FETCH_AUTH_FAILURE,
    payload: {
      error: err,
    },
  };
}

export function logout(dispatch) {
  const actionLogout = {
    type: LOGOUT,
  };

  dispatch(actionLogout);
}

// ACTIONS TO COLLECTIONS

export const FETCH_COLLECTIONS_STARTED = "FETCH_COLLECTIONS_STARTED";
export const FETCH_COLLECTIONS_SUCCESS = "FETCH_COLLECTIONS_SUCCESS";
export const FETCH_COLLECTIONS_FAILURE = "FETCH_COLLECTIONS_FAILURE";

export function fetchCollections(url, request, dispatch) {
  dispatch(fetchCollectionsStarted);
  const success = (res) => dispatch(fetchCollectionsSuccess(res));
  const failure = (err) => dispatch(fetchCollectionsFailure(err));
  makeHTTPRequest(url, request, success, failure);
}

export function resetCollection(dispatch) {
  dispatch(fetchCollectionsStarted());
}

function fetchCollectionsStarted() {
  return {
    type: FETCH_COLLECTIONS_STARTED,
  };
}

function fetchCollectionsSuccess(res) {
  return {
    type: FETCH_COLLECTIONS_SUCCESS,
    payload: {
      data: { ...res },
    },
  };
}

function fetchCollectionsFailure(message) {
  return {
    type: FETCH_COLLECTIONS_FAILURE,
    payload: {
      error: message,
    },
  };
}

// export const FETCH_ADD_TO_COLLECTIONS_SUCCESS = "FETCH_ADD_TO_COLLECTIONS_SUCCESS";
// export const FETCH_ADD_TO_COLLECTIONS_FAILURE = "FETCH_ADD_TO_COLLECTIONS_FAILURE";

export function fetchAddToCollections(url, request, dispatch) {
  const success = (res) => {
    dispatch(fetchDetailsSuccess(res));
  };
  const failure = (err) => dispatch(fetchDetailsFailure(err));
  makeHTTPRequest(url, request, success, failure);
}

// function fetchAddToCollectionsSuccess(res) {
//   return {
//     type: FETCH_ADD_TO_COLLECTIONS_SUCCESS,
//     payload: {
//       data: { ...res },
//     },
//   };
// }
//
// function fetchAddToCollectionsFailure(message) {
//   return {
//     type: FETCH_ADD_TO_COLLECTIONS_FAILURE,
//     payload: {
//       error: message,
//     },
//   };
// }

// ACTIONS TO DETAILS OF AN OBJECT FROM COLLETIONS

export const FETCH_DETAILS_STARTED = "FETCH_DETAILS_STARTED";
export const FETCH_DETAILS_SUCCESS = "FETCH_DETAILS_SUCCESS";
export const FETCH_DETAILS_FAILURE = "FETCH_DETAILS_FAILURE";
export const DELETE_DETAILS = "DELETE_DETAILS";

export function fetchDetails(url, request, dispatch) {
  dispatch(fetchDetailsStarted());
  const success = (res) => dispatch(fetchDetailsSuccess(res));
  const failure = (err) => dispatch(fetchDetailsFailure(err));
  makeHTTPRequest(url, request, success, failure);
}

export function resetDetails(dispatch) {
  dispatch(fetchDetailsStarted());
}

export function fetchDetailsStarted() {
  return {
    type: FETCH_DETAILS_STARTED,
  };
}

export function fetchDetailsSuccess(details) {
  return {
    type: FETCH_DETAILS_SUCCESS,
    payload: {
      data: { ...details },
    },
  };
}

export function fetchDetailsFailure(message) {
  return {
    type: FETCH_DETAILS_FAILURE,
    payload: {
      error: message,
    },
  };
}

export function deleteDetails() {
  return {
    type: DELETE_DETAILS,
  };
}
