Vue.component("adminHomePage",{
    data : function(){
        return{

        }
    },
    template:`
    <div>
        <p>Admin prikeroni</p>
        <button v-on:click="registerManager" style="padding: 7px 20px;
        background-color: aqua;">Napravi menadzera</button>
        <button v-on:click="registerCoach" style="padding: 7px 20px;
        background-color: aqua;">Napravi trenera</button>
    </div>
    `,
    methods : {
        registerManager : function(){
            router.push('/adminHomePage/registerManager/');
        },
        registerCoach : function(){
            router.push('/adminHomePage/registerCoach/');
        }
    }
    
}) 