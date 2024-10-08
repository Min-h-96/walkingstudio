<template>

    <v-data-table
        :headers="headers"
        :items="checkWalkingCount"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'CheckWalkingCountView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            checkWalkingCount : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/checkWalkingCounts'))

            temp.data._embedded.checkWalkingCounts.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.checkWalkingCount = temp.data._embedded.checkWalkingCounts;
        },
        methods: {
        }
    }
</script>

