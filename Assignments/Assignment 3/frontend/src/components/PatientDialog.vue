<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create patient" : "Edit patient" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="patient.id" label="ID" />
          <v-text-field v-model="patient.name" label="Name" />
          <v-text-field v-model="patient.identityCard" label="Identity Card" />
          <v-text-field v-model="patient.CNP" label="CNP" />
          <v-text-field v-model="patient.dateOfBirth" label="Date of Birth" />
          <v-text-field v-model="patient.address" label="Address" />

        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>

          <v-btn v-if="!isNew" @click="deleteItem">Delete</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "PatientDialog",
  props: {
    patient: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.patients
            .create({
              id: this.patient.id,
              name: this.patient.name,
              identityCard: this.patient.identityCard,
              CNP: this.patient.CNP,
              dateOfBirth: this.patient.dateOfBirth,
              address: this.patient.address,

            })
            .then(() => this.$emit("refresh"));
      } else {
        api.patients
            .edit({
              id: this.patient.id,
              name: this.patient.name,
              identityCard: this.patient.identityCard,
              CNP: this.patient.CNP,
              dateOfBirth: this.patient.dateOfBirth,
              address: this.patient.address,
            })
            .then(() => this.$emit("refresh"));
      }
    },
    delete() {
      api.patients.delete(this.patient.id).then(() => this.$emit("refresh"));
    },
  },
  computed: {
    isNew: function () {
      return !this.patient.id;
    },
  },
};
</script>

<style scoped></style>
