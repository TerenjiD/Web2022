Vue.component("viewTrainingCoach",{
    data : function(){
        return{
            trainingToShow : null,
            personalTrainingCheck : false
        }
    },
    template:
    `
    <div>
        <table>
            <tr><td>Name:</td><td>{{trainingToShow.name}}</td></tr>
            <div v-if="personalTrainingCheck">
                <tr><td><button v-on:click="deleteTraining" style="padding: 7px 20px;
                background-color: aqua;">Otkazi</button></td></tr>
            </div>
        </table>
        <div>
            <button v-on:click="goBack" style="padding: 7px 20px;
            background-color: aqua;">Vrati se</button>
        </div>
    </div>
    `,
    mounted(){
        axios.get('rest/coachHomePage/view').then(response => {
            this.trainingToShow = response.data
            let check = response.data.type
            if(check == "PERSONAL_TRAINING"){
                this.personalTrainingCheck = true
            }else{
                this.personalTrainingCheck = false
            }
        })
    },
    methods : {
        deleteTraining : function(event){
            axios.post('rest/coachHomePage/view/delete')
            .then(response => {
                router.push('/coachHomePage')
                alert("Trening obrisan")
            })
            .catch(error => (alert("Ne moze da se otkaze")))
            event.preventDefault();
        },
        goBack : function(){
            router.push('/coachHomePage')
        }
    }
})