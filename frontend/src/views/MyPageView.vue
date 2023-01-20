<template>
    <v-app>
        <v-card elevation="7" shaped style="margin: 70px 250px 0 250px;" height="750px">
        <v-card-title>마이 페이지</v-card-title>
            <v-container fluid>
            <v-row>
                <v-col cols="3">
                <v-subheader>이름</v-subheader>
                </v-col>
                <v-col cols="8">
                <v-text-field
                    label="Name"
                    value=""
                    prefix=""
                    v-model="userInfo.name"
                ></v-text-field>
                </v-col>
            </v-row>

            <v-row>
                <v-col cols="3">
                <v-subheader>아이디</v-subheader>
                </v-col>
                <v-col cols="8">
                <v-text-field
                    label="Id"
                    value=""
                    suffix=""
                    v-model="userInfo.id"
                    readonly
                ></v-text-field>
                </v-col>
            </v-row>

            <v-row>
                <v-col cols="3">
                <v-subheader>비밀번호</v-subheader>
                </v-col>
                <v-col cols="8">
                <v-text-field
                    label="Password"
                    value=""
                    suffix=""
                    type="password"
                    v-model="userInfo.pass"
                ></v-text-field>
                </v-col>
            </v-row>

            <v-row>
                <v-col cols="3">
                <v-subheader>이메일</v-subheader>
                </v-col>
                <v-col cols="8">
                <v-text-field
                    label="Email address"
                    value=""
                    placeholder="example@gmail.com"
                    v-model="userInfo.email"
                ></v-text-field>
                </v-col>
            </v-row>

            <v-row>
                <v-col cols="3">
                <v-subheader>대학생 / 직장인</v-subheader>
                </v-col>
                <v-col cols="8">
                <v-radio-group
                    v-model="row"
                    row
                >
                    <v-radio
                    label="대학생"
                    value="대학생"
                    ></v-radio>
                    <v-radio
                    label="직장인"
                    value="직장인"
                    ></v-radio>
                </v-radio-group>
                </v-col>
            </v-row>

            <v-row>
                <v-col cols="3">
                <v-subheader>주소를 입력해주세요</v-subheader>
                </v-col>
                <v-col cols="4">
                <v-text-field type="text" class="addr1" id="address" placeholder="서울특별시 서초구 -" v-model="userInfo.address"></v-text-field>
                </v-col>
                <v-col cols="1">
                <b-button class="w-button3" @click="openDaumPostcode">주소 검색</b-button>
                <div ref="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
                    <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" @click="foldDaumPostcode" alt="접기 버튼">
                </div>
                </v-col>
                <v-col cols="1">
                <b-button variant="success" @click="updateUser()">수정 하기</b-button>
                </v-col>
                <v-col cols="1">
                <b-button variant="success" @click="userDelete(userInfo)">삭제 하기</b-button>
                </v-col>
            </v-row>
            </v-container>
        </v-card>
    </v-app>
</template>
  
<script>
import { mapState, mapActions } from "vuex";
const memberStore = "memberStore";
export default {
    name : "MyPageView",
    data () {
      return {
        row: null,
        user : {
          name : "",
          id : "",
          pass : "",
          email : "",
          type : "",
          address : "",
        }
      }
    },
    computed: {
        ...mapState(memberStore, ["userInfo"]),
    },
    updated() {
        this.user.type = this.row;
    },
    methods : {
        ...mapActions(memberStore, ["userDelete", "userUpdate"]),
        updateUser(){
            console.log(this.userInfo);
            this.user.id = this.userInfo.id;
            this.user.name = this.userInfo.name;
            this.user.pass = this.userInfo.pass;
            this.user.email = this.userInfo.email;
            this.user.address = this.userInfo.address;
            
            this.userUpdate(this.user);
            this.$router.push({name : "index"});
        },
        openDaumPostcode () {
        const elementWrap = this.$refs.wrap
        // 현재 scroll 위치를 저장해놓는다.
        const currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop)
        new window.daum.Postcode({
            oncomplete: (data) => {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                let addr = '' // 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                // document.getElementById('address').value = addr
                this.userInfo.address = addr

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                elementWrap.style.display = 'none'

                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                document.body.scrollTop = currentScroll
            },
            // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            onresize: function (size) {
                elementWrap.style.height = size.height + 'px'
            },
            width: '100%',
            height: '100%'
        }).embed(elementWrap)
        elementWrap.style.display = 'block'
        },
        foldDaumPostcode () {
            const elementWrap = this.$refs.wrap
            elementWrap.style.display = 'none'
        },
    }
}
</script>

<style>

</style>