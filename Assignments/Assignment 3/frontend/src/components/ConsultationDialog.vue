<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create consultation" : "Edit consultation" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="consultation.id" label="ID" />
          <v-text-field v-model="consultation.doctorId" label="Name" />
          <v-text-field v-model="consultation.patientId" label="Identity Card" />
          <v-text-field v-model="consultation.diagnosis" label="CNP" />
          <v-text-field v-model="consultation.dateOfConsultation" label="Date of Birth" />

        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>

          <v-btn v-if="!isNew" @click="deleteConsultation">Delete</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";
export default {
  name: "ConsultationDialog",
  props: {
    consultation: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.consultations
            .create({
              id: this.consultation.id,
              doctorId: this.consultation.doctorId,
              patientId: this.consultation.patientId,
              diagnosis: this.consultation.diagnosis,
              dateOfConsultation: this.consultation.dateOfConsultation,
            })
            .then(() => this.$emit("refresh"));
      } else {
        api.consultations
            .edit({
              id: this.consultation.id,
              doctorId: this.consultation.doctorId,
              patientId: this.consultation.patientId,
              diagnosis: this.consultation.diagnosis,
              dateOfConsultation: this.consultation.dateOfConsultation,
            })
            .then(() => this.$emit("refresh"));
      }
    },
    deleteConsultation() {
      api.consultations.delete(this.consultation.id).then(() => this.$emit("refresh"));
    },
  },
  computed: {
    isNew: function () {
      return !this.patient.id;
    },
  },
};
</script>

<style scoped>

</style>