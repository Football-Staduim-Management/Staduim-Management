<template>
  <div>
    <div class="centered-container">
      <md-content class="md-elevation-3">
        <div class="title">
          <div class="md-title">Staduim Management Application</div>
          <div class="md-body-1" v-if="stepIndicator === 0">
            Please fill the following informations
          </div>
        </div>

        <form class="form" v-if="stepIndicator === 0">
          <md-field>
            <label>E-mail</label>
            <md-input v-model="admin.email" autofocus></md-input>
          </md-field>

          <md-field md-has-password>
            <label>Password</label>
            <md-input v-model="admin.password" type="password"></md-input>
          </md-field>

          <md-field md-has-password>
            <label>Repeat your password</label>
            <md-input
                v-model="admin.repeatedPassword"
                type="password"
            >
            </md-input>
          </md-field>

          <div v-if="admin.passwordDontMatch">
            <label style="color: red; font-weight: bold">
              Please fill in the informations correctly
            </label>
            <br />
          </div>

          <div class="actions md-layout md-alignment-center-space-between">
            <md-button class="md-raised md-primary" @click="signUpStepOne">SignUp Step 1</md-button>
          </div>
          <div class="loading-overlay" v-if="loading">
            <md-progress-spinner md-mode="indeterminate" :md-stroke="2"></md-progress-spinner>
          </div>
        </form>
        <form class="form" v-if="stepIndicator === 1">
          <chart-card style="height: 20%; width: 50%" v-for="offer in offers" :key="offer.offerId">
            <template slot="header">
              <div style="height : 75%; width : 75%">
                 <img
                      :src="offer.imagePath"
              />
              </div>
              <h4 class="title" style="color : black ; font-weigh : bold">
                  {{ offer.offerType }} Offer
              </h4>
            </template>
            <template slot="content">
              <div style="font-size: 75%; font-weight : bold">
                <md-radio
                        v-model="offer.selectedPayement"
                        :value="offer.payementType.annualPrice"
                >Yearly: {{ offer.payementType.annualPrice }}€</md-radio
                >
                <md-radio
                        v-model="offer.selectedPayement"
                        :value="offer.payementType.monthlyPrice"
                        class="md-primary"
                >Monthly: {{ offer.payementType.monthlyPrice }}€</md-radio
                >
                <br />
                <br />
                <p class="category" style="color: black; font-weight: bold">
                  Manage {{ offer.offerType === "Gold" ? "more than" : 'less than' }} 10 staduim
                </p>
              </div>
            </template>
            <template slot="footer">
              <div class="stats">
                  <md-button class="md-primary" @click="onSelectOffer(offer)">Continue</md-button>
              </div>
            </template>
          </chart-card>
          <div class="loading-overlay" v-if="loading">
            <md-progress-spinner
                    md-mode="indeterminate"
                    :md-stroke="2"
            ></md-progress-spinner>
          </div>
        </form>
      </md-content>
    </div>
  </div>
</template>

<script>
import ChartCard from "../components/Cards/ChartCard";
export default {
  name: "App",
  data() {
    return {
      loading: false,
      stepIndicator: 0,
      admin: {
        email: "",
        password: "",
        repeatedPassword: "",
        passwordDontMatch: false
      },
      offers: [
        {
          offerId: 1,
          offerType: "Gold",
          payementType: { annualPrice: 100, monthlyPrice: 10 },
          imagePath: "https://mkonlinecoaching.com/wp-content/uploads/2019/03/gold-504x600-e1561904291809.png",
          selectedPayement : null
        },
        {
          offerId: 2,
          offerType: "Silver",
          payementType: { annualPrice: 60, monthlyPrice: 6 },
          imagePath: "https://previews.123rf.com/images/alonastep/alonastep1812/alonastep181200276/113334455-silver-shield-shape-icon-3d-gray-emblem-sign-isolated-on-white-background-symbol-of-security-power-p.jpg",
          selectedPayement : null
        }
      ]
    };
  },
  components: {
    ChartCard
  },
  methods: {
    signUpStepOne() {
      if (
        this.admin.password === this.admin.repeatedPassword &&
        this.admin.password != "" &&
        this.admin.repeatedPassword != ""
      ) {
        this.loading = true;
        this.stepIndicator++;
        setTimeout(() => {
          this.loading = false;
        }, 700);
        return;
      }
      this.admin.passwordDontMatch = true;
      return;
    },
    onSelectOffer(offer) {
    console.log(offer); 
    console.log(this.admin); 
    console.log(this.selectedPayement); 
    setTimeout(() => {
        this.loading = false;
        this.$router.push("/signin");
      }, 2000);    }
  }
};
</script>

<style lang="scss">
.centered-container {
  background-image: url("../assets/img/signInBack.jpg");
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  height: 100vh;
  .title {
    text-align: center;
    margin-bottom: 30px;
    img {
      margin-bottom: 16px;
      max-width: 80px;
    }
  }
  .actions {
    .md-button {
      margin: 0;
    }
  }
  .form {
    margin-bottom: 60px;
  }
  .background {
    position: absolute;
    height: 100%;
    width: 100%;
    top: 0;
    bottom: 0;
    right: 0;
    left: 0;
    z-index: 0;
  }
  .md-content {
    z-index: 1;
    padding: 40px;
    width: 100%;
    max-width: 400px;
    position: relative;
  }
  .loading-overlay {
    z-index: 10;
    top: 0;
    left: 0;
    right: 0;
    position: absolute;
    width: 100%;
    height: 100%;
    background: rgba(255, 255, 255, 0.9);
    display: flex;
    align-items: center;
    justify-content: center;
  }
}
</style>
