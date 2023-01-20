<template>
  <v-app>
    <v-card elevation="7" shaped style="margin: 70px 250px 0 250px;" height="750px">
      <v-card-title>회원가입</v-card-title>
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
                v-model="user.name"
              ></v-text-field>
            </v-col>
          </v-row>
      
          <v-row>
            <v-col cols="3">
              <v-subheader>아이디</v-subheader>
            </v-col>
            <v-col cols="6">
              <v-text-field
                label="Id"
                value=""
                suffix=""
                v-model="user.id"
                
              ></v-text-field>
              <div v-if="!isValidId">
                유효하지 ID 입니다.
              </div>
            </v-col>
            <v-col cols="2">
              <b-button variant="success" @click="check()">아이디 중복 확인</b-button>
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
                placeholder="영어 대/소문자, 숫자가 1개 이상, 8자이상 16자 이하"
                v-model="user.pass"
                @blur="passwordValid()"
              ></v-text-field>
              <div v-if="!passwordValidFlag">
                유효하지 않은 비밀번호 입니다.
              </div>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="3">
              <v-subheader>비밀번호 확인</v-subheader>
            </v-col>
            <v-col cols="8">
              <v-text-field
                label="Password Check"
                value=""
                suffix=""
                type="password"
                placeholder="비밀번호 확인"
                v-model="passcheck"
                @blur="passwordCheckValid"
              ></v-text-field>
              <div v-if="!passwordCheckFlag">
                비밀번호가 동일하지 않습니다.
              </div>
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
                v-model="user.email"
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
            <v-col cols="5">
              <v-text-field type="text" class="addr1" id="address" placeholder="서울특별시 서초구 -" v-model="user.address"></v-text-field>
            </v-col>
            <v-col cols="2">
              <b-button class="w-button3" @click="openDaumPostcode">주소검색</b-button>
              <div ref="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
                <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" @click="foldDaumPostcode" alt="접기 버튼">
              </div>
            </v-col>
            <v-col cols="2">
              <b-button variant="success" @click="regist()">가입하기</b-button>
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
  name : "RegistView",
  data () {
    return {
      row: null,
      passwordValidFlag: false,
      passwordCheckFlag: true,
      passcheck: "",
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
    ...mapState(memberStore, ["isValidId"]),
  },
  updated() {
    this.user.type = this.row;
  },
  methods : {
    ...mapActions(memberStore, ["userRegist", "idCheck"]),
    regist(){
      if(!this.isValidId){
        alert("ID를 변경하세요");
        return;
      }
      if(!this.passwordValidFlag){
        alert("비밀번호를 확인하세요");
        return;
      }
      if(!this.passwordCheckFlag){
        alert("비밀번호가 일치하지 않습니다");
        return;
      }
      this.userRegist(this.user);
      // console.log(this.user);
    },
    async check(){
      console.log(this.user);
      await this.idCheck(this.user);
    },
    passwordValid () {
      if (/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}$/.test(this.user.pass)) {
        this.passwordValidFlag = true
      } else {
        this.passwordValidFlag = false
      }
    },
    passwordCheckValid () {
      if (this.user.pass === this.passcheck) {
        this.passwordCheckFlag = true
      } else {
        this.passwordCheckFlag = false
      }
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
            this.user.address = addr

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
#app{
  background-color: rgb(244,243,238);
}
</style>