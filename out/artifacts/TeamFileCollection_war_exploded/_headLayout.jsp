<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta name="renderer" content="webkit">
<meta name="baidu-site-verification" content="qLDoHdGnb64RHlkm">
<meta name="alexaVerifyID" content="SIgQikd9LazsFz9M1vPBaQyC4Gw">
<meta name="tower-version" content="20.08.21">
<link rel="dns-prefetch" href="https://avatar-alioss.tower.im/">
<link rel="dns-prefetch" href="https://atttachments.tower.im/">
<link rel="shortcut icon" href="https://tower.im/favicon.ico?v=2" type="image/x-icon" id="favicon">
<link rel="apple-touch-icon" sizes="180x180" href="https://tower.im/apple-touch-icon.png">
<link rel="manifest" href="https://tower.im/site.webmanifest">
<link rel="mask-icon" href="https://tower.im/safari-pinned-tab.svg" color="#44acb6">
<meta name="msapplication-TileColor" content="#44acb6">
<meta name="theme-color" content="#ffffff">

<link rel="stylesheet" media="all" href="./css/desktop-2f3f812d.css">

<link rel="stylesheet" media="all" href="./css/tr_organization-dad0b220.css">
<link rel="stylesheet" media="all" href="./css/tr_repository-10ca2615.css">

<meta name="csrf-param" content="authenticity_token">
<meta name="csrf-token" content="UzxVyWcKQinMPgnzVsnxQsP9ilBl+GUnNL2POcEYZTGTdfz8jhmboXBNQNkNy8OrmggR9IgFXb9P1T305eJS2g==">

<script src="js/jquery-3.2.1.js"></script>
<script src="js/bootstrap.js" ></script>
<script src="js/team.js"></script>

<style>

    body {
        background: #F4F7ED;
        background-attachment: fixed;
        background-size: cover;
    }
    .intro {
        display: flex;
        flex-direction: row;
        font-size: 40px;
        margin-top:80px;
        height:220px;
        text-align: center;
    }
    .intro-part {
        flex: 1;
    }
    .intro-num {
        color: #44acb6;
        font-size: 50px;
    }
    .downOut{
        position:absolute;
        right: 0px;
        width: 100px;
        top:40px;
        background-color: white;
        text-align: center;
        font-size: 12px;
        display: none;
    }
    .downOut a{
        color: black;
        margin:5px ;
        display: block;
    }
    .downOut a:hover{
        color:#44acb6;
    }

    .modal{
        width: 600px;

        position: absolute;
        top: 100px;
        left: 50%;
        margin-left: -300px;
        background-color: white;
        border: 1px solid gray;
        padding: 20px;
        border-radius: 10px;
    }
    .modal textarea{
        width: 560px;
        height: 50px;
    }
    .fade{
        display: none;
    }
    .form-group input{
        display: inline;
    }
</style>
<script>

    $(function () {
        $(".account-info").click(function () {
            $(".downOut").toggle("fast");
        });
    })

</script>

<script>
    $(function () {
        var msg="${msg}";
        if(msg!=""){
            $("#msg").html(msg);
            $("#msgModal").show("slow");
            $("#msgModal").hide("slow");
        }
    })
</script>
