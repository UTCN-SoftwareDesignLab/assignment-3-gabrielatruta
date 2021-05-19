import authHeader, { BASE_URL, HTTP } from "../http";

export default {

    findAll(){
        return HTTP.get(BASE_URL + "/consultations", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(item) {
        return HTTP.post(BASE_URL + "/consultations", item, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(item) {
        return HTTP.put(BASE_URL + "/consultations/" + item.id, item, {
            headers: authHeader(),
        }).then((response) => {
            return response.data;
        });
    },
    delete(id) {
        return HTTP.delete(BASE_URL + "/consultations/" + id, {
            headers: authHeader(),
        }).then((response) => {
            return response.data;
        });
    },
};
