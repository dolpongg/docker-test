import { apiInstance } from "./index.js";

const api = apiInstance();

async function amountDeal(amountInfo, success, fail){
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/question/deal/${amountInfo.now}/${amountInfo.low}/${amountInfo.high}/${amountInfo.flag}`).then(success).catch(fail);
}

async function amountRent(amountInfo, success, fail){
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/question/rent/${amountInfo.now}/${amountInfo.low}/${amountInfo.high}/${amountInfo.flag}`).then(success).catch(fail);
}

async function location(amountInfo, success, fail){
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/question/location/${amountInfo.now}`).then(success).catch(fail);
}

async function finish(amountInfo, success, fail){
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/question/final/${amountInfo.dealAmount}/${amountInfo.rentMoney}/${amountInfo.distance}/${amountInfo.userid}`).then(success).catch(fail);
}

export { amountDeal, amountRent, location, finish };
