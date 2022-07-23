Vue.component("adminHomePage",{
    data : function(){
        return{
            adminDTO : null
        }
    },
    template:`
    <div>
        <p>Admin prikeroni {{adminDTO.name}}</p>
        <button v-on:click="registerManager" style="padding: 7px 20px;
        background-color: aqua;">Napravi menadzera</button>
        <button v-on:click="registerCoach" style="padding: 7px 20px;
        background-color: aqua;">Napravi trenera</button>
        <button v-on:click="changeInfoAdmin" style="padding: 7px 20px;
        background-color: aqua;">Izmeni podatke</button>
    </div>
    `,mounted(){
        axios.get('/rest/adminHomePage/admin').then(response => (this.adminDTO=response.data));
    },
    methods : {
        registerManager : function(){
            router.push('/adminHomePage/registerManager/');
        },
        registerCoach : function(){
            router.push('/adminHomePage/registerCoach/');
        },
        changeInfoAdmin : function(event){
            router.push('/adminHomePage/changeInfoAdmin/')
        }
    }
    
}) 