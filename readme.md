# How to use
/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  View,
  NativeModules,
  DeviceEventEmitter
} from 'react-native';

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' +
    'Cmd+D or shake for dev menu',
  android: 'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});

type Props = {};
export default class App extends Component<Props> {
  componentDidMount(){
      DeviceEventEmitter.addListener('onScanningResult',this.onScanningResult);
  }
  onScanningResult = (e)=> {
    alert("监听触发啦")
    console.log(e.result);
    // DeviceEventEmitter.removeListener('onScanningResult',this.onScanningResult);//移除扫描监听
  }
  log(){
    // NativeModules.BGNativeModuleExample.testPrint("Jack", {
    //     height: '1.78m',
    //     weight: '7kg'
    // });
    NativeModules.BGNativeModuleExample.testToast("aa")
  }
  callback(){
    NativeModules.BGNativeModuleExample.getNativeClass(name => {
      console.log("nativeClass: ", name);
      alert(name)
    });
  }
  promiseBack(){
    NativeModules.BGNativeModuleExample.testPromises(false)
      .then(result => {
          alert("ok")
          console.log("result is ", result);
      })
      .catch(result => {
          alert("no")
          console.log("result = ", result);
      });
  }
  androidFinal(){
    // alert(NativeModules.BGNativeModuleExample.BGModuleName)
    alert(NativeModules.BGNativeModuleExample.c)
  }
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
        <Text style={styles.instructions}>
          To get started, edit App.js
        </Text>
        <Text style={styles.instructions}>
          {instructions}
        </Text>
        <Text onPress={()=>this.log()}>点击</Text>
        <Text onPress={()=>this.callback()}>回调函数</Text>
        <Text onPress={()=>this.promiseBack()}>Promise回调</Text>
        <Text onPress={()=>this.androidFinal()}>常量</Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
  
注释：添加module时候的build.gradle的SDK要和主工程的SDK相同最好