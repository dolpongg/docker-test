import { apiInstance } from "./index.js";

const api = apiInstance();

async function createUser(user, success, fail){
  await api.post(`/user`, JSON.stringify(user)).then(success).catch(fail);
}

async function checkId(id, success, fail){
  await api.get(`/user/check/${id}`).then(success).catch(fail);
}

async function deleteUser(user, success, fail){
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.delete(`/user/${user.id}`, JSON.stringify(user)).then(success).catch(fail);
}

async function updateUser(user, success, fail){
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.put(`/user`, JSON.stringify(user)).then(success).catch(fail);
}

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await api.get(`/user/${userid}`).then(success).catch(fail);
}

// createUser: function ({ commit }, user) {
//   console.log(user)
//   const API_URL = `http://localhost:9999/userapi/user`;
//   axios({
//     url: API_URL,
//     method: "POST",
//     data: user,
//   })
//     .then(() => {
//       commit("CREATE_USER", user);
//       alert("등록되었습니다.");
//       router.push("/user");
//     })
//     .catch((err) => {
//       console.log(err);
//     });
// },

export { createUser, checkId, deleteUser, updateUser, login, findById, tokenRegeneration, logout };
