<template>
  <v-card>
    <v-card-title>
      Patients
      <v-spacer></v-spacer>
      <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
      ></v-text-field>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :patients="patients"
        @click:row="editPatient"
    ></v-data-table>

    <PatientDialog
        :opened="dialogVisible"
        :item="selectedItem"
        @refresh="refreshList"
    ></PatientDialog>

  </v-card>
</template>

<script>
import api from "../api";
import PatientDialog from "@/components/PatientDialog";

export default {
  name: "AllPatients",
  components : { PatientDialog },
  data() {
    return {
      patients: [],
      search: "",
      headers: [
        {
          text: "ID",
          align: "start",
          value: "id",
        },
        {text: "Name", value: "name"},
        {text: "Identity Card", value: "identityCard"},
        {text: "CNP", value: "CNP"},
        {text: "Birthday", value: "dateOfBirth"},
        {text: "Address", value: "address"},

      ],
      dialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    editPatient(patient) {
      this.selectedItem = patient;
      this.dialogVisible = true;
    },
    // async searchPatient() {
    //   this.patients = await  api.patients.allPatients(this.search);
    // },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedItem = {};
      this.patients = await api.patients.allPatients();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>