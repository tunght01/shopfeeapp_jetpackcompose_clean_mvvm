package com.example.shopfeeap_mvvm.data.local.extention

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt


@Composable
fun HeaderIconBack(title:String, onClickBack:()->Unit){
    Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
        IconButton(onClick = { onClickBack() }) {
            Icon(Icons.Default.ArrowBack, contentDescription = null)
        }
        Text(text = title, style = TextStyle(fontSize = 18.sp))
    }


}
@Composable
fun DividerLine(){
    Divider(
        color = Color.LightGray, // Màu sắc của đường line
        thickness = 4.dp,  // Độ dày của đường line
        modifier = Modifier.padding(vertical = 5.dp) // Khoảng cách trên dưới của Divider
    )
}
@Composable
fun DividerLine2(){
    Divider(
        color = Color.LightGray, // Màu sắc của đường line
        thickness = 1.dp,  // Độ dày của đường line
        modifier = Modifier.padding(vertical = 5.dp) // Khoảng cách trên dưới của Divider
    )
}
@Composable
fun textFileWhite(title: String,modifier: Modifier=Modifier){
    Text(text = title, modifier = Modifier, style = TextStyle(color = Color.White))
}
@Composable
fun textFileWhiteBold(title: String,modifier: Modifier=Modifier){
    Text(text = title, modifier = Modifier, style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold))
}
@Composable
fun FooterFixed(price:Float, textButton:String, onClickButton:()->Unit
){
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp, vertical = 10.dp)
        .background(
            color = Color("#F4EFEB".toColorInt()),
            shape = RoundedCornerShape(5.dp)
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){

        Column (modifier = Modifier
            .padding(10.dp)){
            Text(text = "Tổng tiền")
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = FomartUtility().format(price = price.toInt()),
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }
        Button(onClick = { onClickButton() }, modifier = Modifier.wrapContentSize(), colors = ButtonDefaults.run {
            buttonColors(
                 containerColor = Color("#5D4037".toColorInt()
                ),
                contentColor = Color.White
            )
        }) {
            Text(text = textButton, maxLines = 1, textAlign = TextAlign.Center, style = TextStyle(color = Color.White))
        }

    }
}


//@Composable
//fun getUsername(context: Context, storeUserEmail: StoreUserEmail):String{
//    var username by remember {
//        mutableStateOf("")
//    }
//    LaunchedEffect(Unit) {
//        username = storeUserEmail.fetchEmail() // Gọi hàm fetchEmail()
//
//    }
//    return username
//}
@Composable
fun CheckBoxCustom(isChecked:Boolean, onCheckedChange:(Boolean)->Unit){
    Checkbox(checked = isChecked, onCheckedChange = onCheckedChange,
        colors = CheckboxDefaults.colors(
            Color("#5D4037".toColorInt())
        ))
}
@Composable
fun FooterFixedText(title: String, chose:String,onClickChoseAddress:(String)->Unit
){
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp, vertical = 10.dp)
        .background(
            color = Color("#5D4037".toColorInt()),
            shape = RoundedCornerShape(5.dp)
        )
        .size(40.dp)
        .clickable {
            onClickChoseAddress(chose)
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){

        Text(text = "ĐỒNG Ý", color = Color.White)

    }
}
