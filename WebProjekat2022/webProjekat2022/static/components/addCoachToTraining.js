Vue.component("addCoachToTraining",{
    data : function(){
        return{
            coaches : null,
            content : null,
            coachToSend : {}
        }
    },
    template:
    `
    <div>
        <table>
            <tr>
                <td>Ime sadrzaja:</td>
                <td>{{content.name}}</td>
            </tr>
            <tr v-for="p in coaches">
                <td>
                    {{p.name}} 
                </td>
                <td>
                    {{p.lastName}}
                </td>
                <td><button v-on:click="addCoachToContent(p)" style="padding: 7px 20px;
                        background-color: aqua;">Dodaj trenera</button></td>
            </tr>
        </table>
    </div>
    `,
    mounted(){
        axios.get('/rest/managerHomePage/setContent/').then(response => (this.content = response.data));
        axios.get('/rest/managerHomePage/getCoaches/').then(response => (this.coaches = response.data));
        
    },
    methods : {
        addCoachToContent : function(coach){
            axios.post('rest/managerHomePage/addCoach/',coach);
            router.push('/managerHomePage/')
        }
    }
})