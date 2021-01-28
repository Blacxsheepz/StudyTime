<template>
  <v-app>
    <v-app-bar app color="#BDBDBD">
      <v-toolbar-title class="headline text-uppercase">
        <span>Recruitment </span>
        <span class="font-weight-light" > System</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn
        text
        href="https://github.com/vuetifyjs/vuetify/releases/latest"
        target="_blank"
      >
        <span class="mr-2"></span>
      </v-btn>
    </v-app-bar>

    <v-content>
      <!-- <VideoRental/> -->
      <router-view />
    </v-content>
  </v-app>
</template>

<script>

export default {
  name: 'App',
  data: () => ({
   
  }),
};
</script>
