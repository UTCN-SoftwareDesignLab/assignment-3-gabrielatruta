<template>
  <v-card>
    <v-card-title>
      Consultations
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
        :consultations="consultations"
        @click:row="edit"
    ></v-data-table>
    <ConsultationDialog
        :opened="dialogVisible"
        :item="selectedItem"
        @refresh="refreshList"
    ></ConsultationDialog>
  </v-card>
</template>

<script>
import ConsultationDialog from "@/components/ConsultationDialog";
import api from "@/api";

export default {
  name: "AllConsultations",
  components: { ConsultationDialog },
  data() {
    return {
      consultations: [],
      search: "",
      headers: [
        {
          text: "ID",
          align: "start",
          value: "id",
        },
        { text: "Doctor_ID", value: "doctorId" },
        { text: "Patient_ID", value: "patientId" },
        { text: "Diagnosis", value: "diagnosis" },
        { text: "Date", value: "dateOfConsultation" },
      ],
      dialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    edit(consultation) {
      this.selectedItem = consultation;
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedItem = {};
      this.items = await api.consultations.findAll();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>