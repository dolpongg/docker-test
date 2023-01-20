import { apiInstance } from "./index.js";
// import qs from 'qs';

const api = apiInstance();

function sidoList(success, fail) {
  api.get(`/location`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/location/` + params.sido).then(success).catch(fail);
}

function dongList(params, success, fail) {
  console.log(params.sido, params.gugun); 
  api.get(`/location/` + params.sido + `/` + params.gugun).then(success).catch(fail);
}

async function houseList(params, success, fail) {
  await api.get(`/main/` + params.sido + `/` + params.gugun + `/` + params.dong).then(success).catch(fail);
}

async function houseDetail(params, success, fail) {
  console.log("ok", params.sido, params.gugun);
  await api.get(`/main/` + params.sido + `/` + params.gugun + `/` + params.dong + `/` + params.aptcode + `/` + params.userid).then(success).catch(fail);
}

export { sidoList, gugunList, dongList, houseList, houseDetail };
