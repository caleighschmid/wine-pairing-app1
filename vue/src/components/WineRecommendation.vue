<template>
    <div>
        <h2 class="section-title">Wine Recommendation</h2>
        <div v-if="wineRec">
            <div class="pairings">
                <h3>Recommended Pairings</h3>
                <ul class="pairings-list">
                    <li v-for="wine in wineRec.wines" :key="wine">{{ capitalize(wine) }}</li>
                </ul>
            </div>
        </div>

        <div v-if="wineRec && wineRec.recBlurb">
            <div class="opinion">
                <h3>Our Opinion</h3>
                <p>{{ wineRec.recBlurb }}</p>
            </div>
        </div>

        <div v-if="wineRec.specificRec">
            <div class="recommendation">
                <h3>Our Recommendation</h3>
                <h4>{{ wineRec.specificRec.title }}</h4>
                <img :src="wineRec.specificRec.imageUrl" alt="Wine Image" />
                <p>{{ wineRec.specificRec.description }}</p>
                <p>${{ formatPrice(wineRec.specificRec.price) }}</p>
            </div>
        </div>


    </div>
</template>


<script>
export default {
    props: {
        wineRec: {
            type: Object,
            default: () => ({}), // Default to an empty object
        },
    },
    methods: {
        capitalize: function (value) {
            if (!value) return '';
            value = value.toString();
            return value.charAt(0).toUpperCase() + value.slice(1);
        },
        formatPrice: function (price) {
            // Remove the dollar sign and any other non-numeric characters
            const numericPrice = parseFloat(price.replace(/[^0-9.]/g, ''));

            return isNaN(numericPrice) ? 'N/A' : numericPrice.toFixed(2);
        },
    },
};
</script>

<style scoped>
.section-title {
    font-size: 24px;
    margin-bottom: 10px;
}

.pairings-list {
    list-style-type: none;
    padding: 0;
    margin: 0;
}

.pairings-list li {
    background-color: #f5f5f5;
    padding: 8px;
    margin-bottom: 5px;
    border-radius: 5px;
}

.pairings,
.opinion,
.recommendation {
    margin-bottom: 20px;
}

.recommendation img {
    max-width: 100%;
    height: auto;
}
</style>