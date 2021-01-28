<template>
  <v-card class = "mx-auto" height="100%" width="100%">
    <v-navigation-drawer 
    absolute
    src="https://images.unsplash.com/photo-1496902526517-c0f2cb8fdb6a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80&fbclid=IwAR1-eUROQcdh14MVvwIBS_gxUrlH5w140NIhIFpJL0QOmh9zlHdksOKrq0Y"
    width="100%"
    parmanent>

  <v-container>
    <v-card class="mx-auto" max-width="350" color="#424242" :elevation="13" dark>
    <v-row justify="center">
      <v-toolbar-title>
        <h1 class="font-weight-CONDENSED" dark>Job Post</h1>
         </v-toolbar-title>
    </v-row>
    </v-card>
<br>
    <v-card
        class="mx-auto"
        max-width="800"
        color="#EEEEEE"
        outlined
        :elevation="7"
    >

    <v-row justify="center">
      <v-col cols="8">
        <v-form >
           
            <v-row style="height: 95px;" justify="center">
            <v-col cols="10" >
              <v-text-field
              readonly
                solo
               loading color="blue accent-1"
               
               class="font-weight-bold"
                label="Company"
                v-model="company"
                :rules="[(v) => !!v || 'This field is required']"
                required 
               prepend-icon= "mdi-home-modern"     
               outlined
               
              ></v-text-field>
            </v-col>    
            </v-row>
            
             
            <v-row justify="center">
              <v-col cols="7">
                <v-select
                  label="Position"
                  solo
                  loading color="blue accent-1"
                  v-model="jobPost.positionId"
                  :items="positions"
                  item-text="name"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                  prepend-icon= "mdi-clipboard"
                  outlined
                ></v-select>
              </v-col>
            </v-row>

             
              <v-row justify="center">
            <v-col cols="6">
              <v-text-field
                solo
                loading color="blue accent-1"
                label="Educationlevel"
                v-model="jobPost.educationlevel"
                :items="educationlevels"
                :rules="[(v) => !!v || 'This field is required']"
                required
                clearable
                prepend-icon="mdi-school"
                outlined
              ></v-text-field>
            </v-col>

            <v-col cols="6">
              <v-text-field
                solo
                loading color="blue accent-1"
                label="Salary"
                v-model="jobPost.salary"
                :items="salarys"
                :rules="[(v) => !!v || 'This field is required']"
                required
                clearable
                prepend-icon = "mdi-cash-multiple"
                outlined
              ></v-text-field>
            </v-col>
          </v-row>

               
              <v-row justify="center">
              <v-col cols="7">
                <v-select
                  label="Benefit"
                  solo
                  loading color="blue accent-1"
                  v-model="jobPost.benefitId"
                  :items="benefits"
                  item-text="name"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                   prepend-icon = "mdi-emoticon"
                  outlined
                ></v-select>
              </v-col>
            </v-row>

          
                <v-row justify="center">
           <v-col cols="12"  md="11" >
         <v-textarea
        rows="5"
          solo
          color="#1E88E5"
          name="other_detial"
          label="otherdetial"
           v-model="jobPost.Other_details"
           :items="Other_details"
           outlined
           rounded
        ></v-textarea>
      </v-col>
     </v-row>

          <v-row justify="center">
            <v-col cols="12">
              <v-btn 
                style="margin-left: 25%;"
                @click="clear" rounded >clear
              </v-btn>
              <v-btn 
                style="margin-left: 25%;"
                @click="savejobPost" rounded :class="{ grey: !valid, black: valid }" :elevation="10" dark>save
              </v-btn>
            </v-col>
          </v-row>
        </v-form>
      </v-col>
    </v-row>

<v-system-bar color="black"></v-system-bar>
<v-system-bar color="black"></v-system-bar>

      </v-card>
      <v-col cols="12">
          <v-btn  x-large
            color = 'grey'
            rounded
            style="margin-left: 77%;" 
            dark @click="back" ><v-icon left>mdi-arrow-left-circle-outline</v-icon>BACK</v-btn>   
      </v-col>
  </v-container>
  </v-navigation-drawer>
  </v-card>
</template>

<script>
import http from "../http-common";

export default {
  name: "jobPost",

  data() {
    return {
      jobPost: {
        positionId: "",
        benefitId: "",
        salary : "",
        educationlevel : "",
        Other_details : "",
        company : "",
      },
     
        salarys : "",
        educationlevels : "",
        Other_details : "",
        company : localStorage.getItem('name'), 
        positions : [],
        benefits : []
    };     
     var firstKey = localStorage.key(0); 
  console.log(firstKey);
  },
  methods: {
  
    getPositions() {
      http
        .get("/position")
        .then(response => {
          this.positions = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
       
    getBenefits() {
      http
        .get("/benefit")
        .then(response => {
          this.benefits = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
     getCompanys() {
      http
        .get("/company")
        .then(response => {
          var firstKey = localStorage.key(0); 
         console.log(firstKey);
          this.firstKey = response.data;
          console.log(response.data); 
        })
        .catch(e => {
          console.log(e);
       });
    },
  
   
   savejobPost() {
     if (
      !this.company ||
      !this.jobPost.positionId ||
      !this.jobPost.educationlevel || 
      !this.jobPost.salary ||
      !this.jobPost.benefitId ||
      !this.jobPost.Other_details 
     ) {
       alert("ใส่ข้อมูลไม่ครบถ้วน");
     
     }else{
      http
        .post(
          "/jobPost/" + localStorage.getItem('id') +
           "/" + this.jobPost.positionId + 
           "/" + this.jobPost.benefitId + 
           "/" + this.jobPost.educationlevel +
            "/" + this.jobPost.salary  + 
            "/" + this.jobPost.Other_details,
            this.jobPost
          
        )
      .then(response => {
          console.log(response);
          this.$router.push("/jobpost")
        
        })
    .catch(e => {
          console.log(e);
        
        });
        this.summitted = true;
        alert("บันทึกเรียบร้อย");
    
     }   
  },
    clear() {
     
        this.jobPost.salary = "";
        this.jobPost.educationlevel = "";
        this.jobPost.Other_details = "";

        this.jobPost.positionId = null;
        this.jobPost.benefitId = null;
    },
    refreshList() {
    
      this.getPositions();
      this.getBenefits();
      
    },
    back(){
    this.$router.push("/companychoice");
    }
  },
    mounted() {
      this.getPositions();
      this.getBenefits();
      this.getCompanys();
  }
};
</script>