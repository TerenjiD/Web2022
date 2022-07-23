Vue.component("coachHomePage",{
    data : function(){
        return{
            coachDTO : null

        }
    },
    template:`
    <div>
        <p>Trener prikeroni {{coachDTO.name}}</p>
        <button v-on:click="changeInfoCoach" style="padding: 7px 20px;
                        background-color: aqua;">Izmeni</button>
    </div>
    `,mounted(){
        axios.get('/rest/coachHomePage/coach').then(response => (this.coachDTO=response.data))
    },
    methods : {
        changeInfoCoach : function(event){
            router.push('/coachHomePage/changeInfoCoach/');
        }
    }
    
}) 