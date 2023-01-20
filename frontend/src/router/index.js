import Vue from 'vue'
import VueRouter from 'vue-router'
import IndexView from '@/views/IndexView'
import MainView from '@/views/MainView'
import LoginView from '@/views/LoginView'
import RegistView from '@/views/RegistView'
import BoardView from '@/views/BoardView'
import RecommendView from '@/views/RecommendView'
import MyPageView from '@/views/MyPageView'
import SupportView from '@/views/SupportView'
import RecommendResult from '@/views/RecommendResult'

import store from "@/store";

Vue.use(VueRouter)

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("memberStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({ name: "login" });
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const routes = [
  {
    path: '/',
    name: 'index',
    component: IndexView
  },
  {
    path: '/main',
    name: 'main',
    component: MainView,
    children: [
      {
        path: 'houseList',
        name: 'houseList',
        component: () => import('@/components/house/HouseList'),
      },
      {
        path: 'houseDetail',
        name: 'houseDetail',
        component: () => import('@/components/house/HouseDetail'),
        children: [
          {
            path: 'houseDetailMain',
            name: 'houseDetailMain',
            component: () => import('@/components/house/item/HouseDetailMain'),
          },
          {
            path: 'houseDetailInfo',
            name: 'houseDetailInfo',
            component: () => import('@/components/house/item/HouseDetailInfo'),
          },
          {
            path: 'houseDetailEvent',
            name: 'houseDetailEvent',
            component: () => import('@/components/house/item/HouseDetailEvent'),
          },
          {
            path: 'houseDetailDis',
            name: 'houseDetailDis',
            component: () => import('@/components/house/item/HouseDetailDis'),
          },
          {
            path: 'houseDetailHealth',
            name: 'houseDetailHealth',
            component: () => import('@/components/house/item/HouseDetailHealth'),
          },
        ]
      },
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/regist',
    name: 'regist',
    component: RegistView
  },
  {
    path: '/mypage',
    name: 'mypage',
    component: MyPageView
  },
  {
    path: '/support',
    name: 'support',
    component: SupportView
  },
  {
    path: '/board',
    name: 'board',
    component: BoardView,
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardlist",
        component: () => import("@/components/board/BoardList"),
      },
      {
        path: "write",
        name: "boardwrite",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardWrite"),
      },
      {
        path: "view/:articleno",
        name: "boardview",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardView"),
      },
      {
        path: "modify",
        name: "boardmodify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardModify"),
      },
      {
        path: "delete/:articleno",
        name: "boarddelete",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardDelete"),
      },
    ],
  },
  {
    path: '/recommend',
    name: 'recommend',
    component: RecommendView,
    children: [
      {
        path: "question1",
        name: "question1",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/recommend/item/QuestionItem1"),
      },
      {
        path: "question2",
        name: "question2",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/recommend/item/QuestionItem2"),
      },
      {
        path: "question3",
        name: "question3",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/recommend/item/QuestionItem3"),
      },
    ]
  },
  {
    path: '/recommendResult',
    name: 'recommendResult',
    component: RecommendResult,
    children: [
      {
        path: 'recommendList',
        name: 'recommendList',
        component: () => import("@/components/recommend/RecommendList"),
      },
      {
        path: 'recommendMap',
        name: 'recommendMap',
        component: () => import("@/components/recommend/RecommendMap"),
      },
      {
        path: 'recommendDetail',
        name: 'recommendDetail',
        component: () => import("@/components/recommend/RecommendDetail"),
        children: [
          {
            path: 'recommendHouseDetailMain',
            name: 'recommendHouseDetailMain',
            component: () => import('@/components/recommend/item/RecommendHouseDetailMain'),
          },
          {
            path: 'recommendHouseDetailInfo',
            name: 'recommendHouseDetailInfo',
            component: () => import('@/components/recommend/item/RecommendHouseDetailInfo'),
          },
          {
            path: 'recommendHouseDetailEvent',
            name: 'recommendHouseDetailEvent',
            component: () => import('@/components/recommend/item/RecommendHouseDetailEvent'),
          },
          {
            path: 'recommendHouseDetailDis',
            name: 'recommendHouseDetailDis',
            component: () => import('@/components/recommend/item/RecommendHouseDetailDis'),
          },
          {
            path: 'recommendHouseDetailHealth',
            name: 'recommendHouseDetailHealth',
            component: () => import('@/components/recommend/item/RecommendHouseDetailHealth'),
          },
        ]
      },
    ]
  },
  
]

const router = new VueRouter({
  routes
})

export default router
