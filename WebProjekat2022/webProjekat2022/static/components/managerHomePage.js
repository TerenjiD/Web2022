Vue.component("managerHomePage",{
    data : function(){
        return{
            userDTO : null
        }
    },
    template:`
    <div>
        <p>Menadzer prikeroni {{userDTO.name}}</p>
        <button v-on:click="changeInfo" style="padding: 7px 20px;
                        background-color: aqua;">Izmeni</button>
    </div>
    `,
    mounted(){
        axios.get('/rest/managerHomePage/manager').then(response => (this.userDTO = response.data));
    },
    methods : {
        changeInfo : function() {
            router.push('/managerHomePage/changeInfoManager/');
        }
    }
    
}) 