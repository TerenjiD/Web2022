Vue.component("addContent",{
    data : function(){
        return{
            contentToSend : {type:null}
        }
    },
    template:
    `
    <div>
        <select type="text" value="" id="type" v-model="contentToSend.type">
            <option>GROUP_TRAINING</option>
            <option>PERSONAL_TRAINING</option>
            <option>SAUNA</option> 
        </select>
        </b>
        <button v-on:click = "createContent"  style="padding: 7px 20px;
                background-color: aqua;">Dodaj</button>
    </div>
    `,
    mounted(){

    },
    methods : {

    }
})