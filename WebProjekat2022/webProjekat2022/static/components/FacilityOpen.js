Vue.component("FacilityOpen",{
    data : function(){
        return{
            trainings : null
        }
    },
    template:
    `
    <div>
    <table v-for="(p, index) in trainings">
    <tr><td>Name:</td>
        <td>{{p.name}}</td>
    </tr>
    <tr><td>Type:</td>
        <td>{{p.type}}</td>
    </tr>
    <tr><td>Coach:</td>
        <td>{{p.coachID}}</td>
    </tr>
    <tr><td>Logo:</td>
        <td>{{p.logo}}</td>
    </tr>
    <tr><td>Description:</td>
        <td>{{p.description}}</td>
    </tr>
    <tr><td>Duration:</td>
        <td>{{p.duration}}</td>
    </tr>
    <tr>
    <td><button v-on:click="beginTraining(p)" style="padding: 7px 20px;
    background-color: aqua;">Zapocni</button></td>
    </tr>
    </table>
    </div>
    `,
    mounted(){
        axios.get('rest/customerHomePage/FacilityOpen/getContents').then(response => (this.trainings = response.data))
    },
    methods : {
        beginTraining : function(p){
            axios.post('rest/customerHomePage/FacilityOpen/beginTraining',p)
            .then(response => {
                alert("Trening zapoceo")
                router.push('/customerHomePage')
            })
        }
    }
})