window.__require = function t(e, n, o) {
    function i(r, c) {
        if (!n[r]) {
            if (!e[r]) {
                var s = r.split("/");
                if (s = s[s.length - 1],
                !e[s]) {
                    var l = "function" == typeof __require && __require;
                    if (!c && l)
                        return l(s, !0);
                    if (a)
                        return a(s, !0);
                    throw new Error("Cannot find module '" + r + "'")
                }
                r = s
            }
            var u = n[r] = {
                exports: {}
            };
            e[r][0].call(u.exports, function(t) {
                return i(e[r][1][t] || t)
            }, u, u.exports, t, e, n, o)
        }
        return n[r].exports
    }
    for (var a = "function" == typeof __require && __require, r = 0; r < o.length; r++)
        i(o[r]);
    return i
}({
    ActionMgr: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "6f8c8uoFNJJzLKZkdh6en9Z", "ActionMgr"),
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var o = cc.easeInOut
          , i = t("./GameLogMgr")
          , a = cc.tween
          , r = function() {
            function t() {}
            return t.shakeHand = function(t) {
                a(t).by(.05, {
                    y: 20
                }).by(.05, {
                    y: -20
                }).union().repeatForever().start()
            }
            ,
            t.scaleNode = function(t, e, n) {
                void 0 === n && (n = .5);
                try {
                    cc.tween(t).to(0, {
                        scale: e,
                        y: -t.height * (1 - e)
                    }).to(n, {
                        scale: 1,
                        y: 0
                    }).start()
                } catch (o) {
                    t.scale = 1,
                    t.y = 0,
                    i.default.log("shake\u52a8\u753b\u5f02\u5e38", o)
                }
            }
            ,
            t.moveUpDownForever = function(t, e, n) {
                void 0 === e && (e = .2),
                void 0 === n && (n = 10),
                cc.tween(t).repeatForever(cc.sequence(cc.moveBy(e, new cc.Vec2(0,n)), cc.moveBy(e, new cc.Vec2(0,-n)))).start()
            }
            ,
            t.rotate = function(t, e) {
                void 0 === e && (e = 3);
                try {
                    cc.tween(t).repeatForever(cc.sequence(cc.rotateTo(e, 180), cc.rotateTo(e, 360))).start()
                } catch (n) {
                    i.default.error("shakeNode\u5f02\u5e38", n, t)
                }
            }
            ,
            t.scaleStop = function(t, e, n, o, a, r) {
                void 0 === e && (e = .1),
                void 0 === n && (n = 10),
                void 0 === o && (o = .3),
                void 0 === a && (a = 1),
                void 0 === r && (r = 1);
                try {
                    if (!t)
                        return;
                    t.scale = r,
                    cc.tween(t).repeatForever(cc.tween().repeat(a, cc.tween().to(e, {
                        scale: 1 + o
                    }, {
                        easing: "smooth"
                    }).to(e, {
                        scale: r
                    }, {
                        easing: "smooth"
                    })).to(e, {
                        angle: 0
                    }).delay(n)).start()
                } catch (c) {
                    i.default.error("shakeNode\u5f02\u5e38", c, t)
                }
            }
            ,
            t.shakeNode = function(t, e, n, o) {
                void 0 === e && (e = .3),
                void 0 === n && (n = 10),
                void 0 === o && (o = 5),
                cc.tween(t).repeat(o, cc.tween().to(e, {
                    angle: -n
                }).to(e, {
                    angle: n
                })).to(e, {
                    angle: 0
                }).start()
            }
            ,
            t.shakeNodeForever = function(t, e, n) {
                void 0 === e && (e = 4),
                void 0 === n && (n = 10);
                try {
                    cc.tween(t).repeatForever(cc.sequence(cc.rotateTo(e, -n), cc.rotateTo(e, n))).start()
                } catch (o) {
                    i.default.error("shakeNode\u5f02\u5e38", o, t)
                }
            }
            ,
            t.shakeStop = function(t, e, n, o, a) {
                void 0 === e && (e = .1),
                void 0 === n && (n = 10),
                void 0 === o && (o = 10),
                void 0 === a && (a = 5);
                try {
                    cc.tween(t).repeatForever(cc.tween().delay(n).repeat(a, cc.tween().to(e, {
                        angle: -o
                    }).to(e, {
                        angle: o
                    })).to(e, {
                        angle: 0
                    })).start()
                } catch (r) {
                    i.default.error("shakeStop\u5f02\u5e38", r, t)
                }
            }
            ,
            t.scaleMove = function(t, e, n) {
                void 0 === e && (e = 3),
                void 0 === n && (n = 2),
                cc.tween(t).repeatForever(cc.tween().delay(e).repeat(n, cc.tween().to(.3, {
                    scale: 1.1
                }).to(0, {
                    scale: 1
                }).to(.3, {
                    scale: 1.2
                }).to(0, {
                    scale: 1
                }))).start()
            }
            ,
            t.moveBigIn = function(t, e) {
                if (void 0 === e && (e = .3),
                !t)
                    return !1;
                try {
                    t.scale = 2,
                    cc.tween(t).to(e, {
                        scale: 1
                    }, o(1)).start()
                } catch (n) {
                    t.scale = 1,
                    i.default.error("moveIn \u5f02\u5e38", n, t)
                }
            }
            ,
            t.moveIn = function(t, e, n) {
                void 0 === e && (e = .4),
                void 0 === n && (n = 0),
                t.scale = n,
                cc.tween(t).to(e, {
                    scale: 1
                }, {
                    easing: "backOut"
                }).start()
            }
            ,
            t.moveTop = function(t, e) {
                void 0 === e && (e = .4),
                t.y = t.height / 2,
                cc.tween(t).to(e, {
                    y: 0
                }, {
                    easing: "smooth"
                }).start()
            }
            ,
            t.moveOut = function(t, e, n) {
                if (void 0 === e && (e = .4),
                void 0 === n && (n = 0),
                !t)
                    return !1;
                try {
                    t.scale = 1,
                    cc.tween(t).to(e, {
                        scale: n
                    }).start()
                } catch (o) {
                    t.scale = 0,
                    i.default.error("moveOut \u5f02\u5e38", o, t)
                }
            }
            ,
            t.moveInSide = function(t, e, n, o) {
                if (void 0 === e && (e = this.TWEEN_FROM_SIDE.TOP),
                void 0 === n && (n = .5),
                void 0 === o && (o = 0),
                !t)
                    return !1;
                this._moveSideNodePosition.hasOwnProperty(t.uuid) || (this._moveSideNodePosition[t.uuid] = t.position.clone());
                try {
                    var a = this._getSideResultPos(t, e);
                    t.position = a,
                    i.default.log("\u8282\u70b9", t.name, "\u754c\u9762\u5916\u5750\u6807\u4e3a\uff1a", a),
                    cc.tween(t).delay(o).to(n, {
                        position: this._moveSideNodePosition[t.uuid].clone()
                    }, {
                        easing: "quartIn"
                    }).start()
                } catch (r) {
                    t.position = this._moveSideNodePosition[t.uuid].clone(),
                    i.default.log("move In Side \u5f02\u5e38", r, t)
                }
            }
            ,
            t.moveOutSide = function(t, e, n) {
                var o = this;
                if (void 0 === e && (e = this.TWEEN_FROM_SIDE.TOP),
                void 0 === n && (n = .5),
                !t)
                    return !1;
                this._moveSideNodePosition.hasOwnProperty(t.uuid) || (this._moveSideNodePosition[t.uuid] = t.position);
                try {
                    var a = this._getSideResultPos(t, e);
                    cc.tween(t).to(n, {
                        position: a
                    }, {
                        easing: "quartIn"
                    }).call(function() {
                        t.position = o._moveSideNodePosition[t.uuid].clone(),
                        i.default.log("end move out side", t.position)
                    }).start()
                } catch (r) {
                    t.position = this._moveSideNodePosition[t.uuid].clone(),
                    i.default.log("move Out Side \u5f02\u5e38", r, t)
                }
            }
            ,
            t._getSideResultPos = function(e, n) {
                var o, a = cc.winSize, r = a.height, c = a.width;
                switch (i.default.log("\u5c4f\u5e55\u5c3a\u5bf8\u5927\u5c0f\u4e3a\uff1a", r, c),
                n) {
                case t.TWEEN_FROM_SIDE.TOP:
                    o = new cc.Vec2(e.x,r + e.height);
                    break;
                case t.TWEEN_FROM_SIDE.DOWN:
                    o = new cc.Vec2(e.x,-r - e.height);
                    break;
                case t.TWEEN_FROM_SIDE.LEFT:
                    o = new cc.Vec2(-c - e.width,e.y);
                    break;
                case t.TWEEN_FROM_SIDE.RIGHT:
                    o = new cc.Vec2(c + e.width,e.y)
                }
                return o
            }
            ,
            t.moveLeftRight = function(e, n, o, a) {
                try {
                    var r = void 0
                      , c = void 0;
                    switch (n) {
                    case t.TWEEN_FROM_SIDE.LEFT:
                    case t.TWEEN_FROM_SIDE.RIGHT:
                        r = new cc.Vec2(e.x - o,e.y),
                        c = new cc.Vec2(e.x + o,e.y);
                        break;
                    case t.TWEEN_FROM_SIDE.TOP:
                    case t.TWEEN_FROM_SIDE.DOWN:
                        r = new cc.Vec2(e.x,e.y - o),
                        c = new cc.Vec2(e.x,e.y + o)
                    }
                    cc.tween(e).repeatForever(cc.sequence(cc.moveTo(a, r), cc.moveTo(a, c))).start()
                } catch (s) {
                    i.default.error("moveLeftRight\u5f02\u5e38", s, e)
                }
            }
            ,
            t.showGradually = function(t, e, n, o, a) {
                void 0 === e && (e = 1),
                void 0 === n && (n = 0),
                void 0 === o && (o = 255),
                void 0 === a && (a = 0);
                try {
                    cc.tween(t).call(function() {
                        t.opacity = a,
                        t.active = !0
                    }).delay(n).to(e, {
                        opacity: o
                    }).start()
                } catch (r) {
                    t.opacity = o,
                    i.default.error("showGradually\u5f02\u5e38", r, t)
                }
            }
            ,
            t.hideGradually = function(t, e, n, o) {
                void 0 === e && (e = 1),
                void 0 === n && (n = 0),
                void 0 === o && (o = 50);
                try {
                    t.active = !0,
                    cc.tween(t).delay(n).to(e, {
                        opacity: o
                    }, {
                        easing: "quartIn"
                    }).call(function() {
                        t.active = !1,
                        t.opacity = 255
                    }).start()
                } catch (a) {
                    t.opacity = o,
                    i.default.error("showGradually\u5f02\u5e38", a, t)
                }
            }
            ,
            t.TWEEN_FROM_SIDE = {
                TOP: 1,
                DOWN: 2,
                LEFT: 3,
                RIGHT: 4
            },
            t._moveSideNodePosition = {},
            t
        }();
        n.default = r,
        cc._RF.pop()
    }
    , {
        "./GameLogMgr": "GameLogMgr"
    }],
    AudioMgr: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "d1f55Ux+bxKBL58UPq/GXsh", "AudioMgr");
        var o = this && this.__decorate || function(t, e, n, o) {
            var i, a = arguments.length, r = a < 3 ? e : null === o ? o = Object.getOwnPropertyDescriptor(e, n) : o;
            if ("object" == typeof Reflect && "function" == typeof Reflect.decorate)
                r = Reflect.decorate(t, e, n, o);
            else
                for (var c = t.length - 1; c >= 0; c--)
                    (i = t[c]) && (r = (a < 3 ? i(r) : a > 3 ? i(e, n, r) : i(e, n)) || r);
            return a > 3 && r && Object.defineProperty(e, n, r),
            r
        }
        ;
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var i = t("./GameLogMgr")
          , a = t("./CacheMgr")
          , r = t("./LoadMgr")
          , c = cc._decorator.ccclass
          , s = function() {
            function t() {}
            return t.backMusic = function(t) {
                void 0 === t && (t = !0),
                0 === a.default.setting.setting.music ? cc.audioEngine.stopMusic() : t ? cc.audioEngine.isMusicPlaying() || r.default.load_AudioClip("bg").then(function(t) {
                    cc.audioEngine.playMusic(t, !0),
                    cc.audioEngine.setMusicVolume(a.default.setting.setting.music)
                }) : cc.audioEngine.stopMusic()
            }
            ,
            t.play = function(t, e, n) {
                return void 0 === e && (e = 1),
                void 0 === n && (n = !1),
                new Promise(function(o) {
                    0 === a.default.setting.setting.audio && (i.default.warn(" \u5f53\u524d\u97f3\u91cf\u9759\u97f3 "),
                    o(!1)),
                    r.default.load_AudioClip(t).then(function(t) {
                        var i;
                        cc.audioEngine.setEffectsVolume(e * a.default.setting.setting.audio),
                        i = cc.audioEngine.playEffect(t, n),
                        o(i)
                    })
                }
                )
            }
            ,
            o([c], t)
        }();
        n.default = s,
        cc._RF.pop()
    }
    , {
        "./CacheMgr": "CacheMgr",
        "./GameLogMgr": "GameLogMgr",
        "./LoadMgr": "LoadMgr"
    }],
    CacheMgr: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "26bdbgdW/1A6KNXjHKHakT2", "CacheMgr"),
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var o = t("../Global")
          , i = function() {
            function t() {
                this._userId = 0,
                this._checkpoint = 0,
                this._gold = 0,
                this._diamond = 0,
                this._stamina = 20,
                this._user_code = "",
                this._openId = "",
                this._lastTimeLogin = 0,
                this._hit = [],
                this._userInfo = null,
                this._newUser = !1,
                this.nowCheckPoint = -1,
                this._isNeedHint = !0,
                this._isAuth = !1,
                this._recall = 0,
                this._hint = 0,
                this._signInCount = 0,
                this._currTimestamp = 0,
                this._setting = {
                    hintNum: 5,
                    setting: {
                        music: 1,
                        audio: 1,
                        vibrate: 1
                    }
                };
                for (var t = Object.keys(this), e = 0; e < t.length; e++)
                    "_" == t[e][0] && this.getData(t[e])
            }
            return Object.defineProperty(t.prototype, "signInCount", {
                get: function() {
                    return this._signInCount
                },
                set: function(t) {
                    this.saveData("_signInCount", t),
                    this._signInCount = t
                },
                enumerable: !1,
                configurable: !0
            }),
            Object.defineProperty(t.prototype, "currTimestamp", {
                get: function() {
                    return this._currTimestamp
                },
                set: function(t) {
                    this.saveData("_currTimestamp", t),
                    this._currTimestamp = t
                },
                enumerable: !1,
                configurable: !0
            }),
            Object.defineProperty(t.prototype, "recall", {
                get: function() {
                    return this._recall
                },
                set: function(t) {
                    this.saveData("_recall", t),
                    this._recall = t
                },
                enumerable: !1,
                configurable: !0
            }),
            Object.defineProperty(t.prototype, "hint", {
                get: function() {
                    return this._hint
                },
                set: function(t) {
                    this.saveData("_hint", t),
                    this._hint = t
                },
                enumerable: !1,
                configurable: !0
            }),
            Object.defineProperty(t.prototype, "isNeedHint", {
                get: function() {
                    return this._isNeedHint
                },
                set: function(t) {
                    this._isNeedHint = t,
                    this.saveData("_isNeedHint", t, !1)
                },
                enumerable: !1,
                configurable: !0
            }),
            Object.defineProperty(t.prototype, "isAuth", {
                get: function() {
                    return this._isAuth
                },
                set: function(t) {
                    this.saveData("_isAuth", t, !1),
                    this._isAuth = t
                },
                enumerable: !1,
                configurable: !0
            }),
            Object.defineProperty(t.prototype, "userId", {
                get: function() {
                    return this._userId
                },
                set: function(t) {
                    this.saveData("_userId", t, !1),
                    this._userId = t
                },
                enumerable: !1,
                configurable: !0
            }),
            Object.defineProperty(t.prototype, "setting", {
                get: function() {
                    return this._setting
                },
                set: function(t) {
                    this.saveData("_setting", t),
                    this._setting = t
                },
                enumerable: !1,
                configurable: !0
            }),
            Object.defineProperty(t.prototype, "userInfo", {
                get: function() {
                    return this._userInfo
                },
                set: function(t) {
                    this.saveData("_userInfo", t, !1),
                    this._userInfo = t
                },
                enumerable: !1,
                configurable: !0
            }),
            Object.defineProperty(t.prototype, "newUser", {
                get: function() {
                    return this._newUser
                },
                set: function(t) {
                    this.saveData("_newUser", t, !1),
                    this._newUser = t
                },
                enumerable: !1,
                configurable: !0
            }),
            Object.defineProperty(t.prototype, "hit", {
                get: function() {
                    return this._hit
                },
                set: function(t) {
                    this.saveData("_hit", t, !1),
                    this._hit = t
                },
                enumerable: !1,
                configurable: !0
            }),
            Object.defineProperty(t.prototype, "lastTimeLogin", {
                get: function() {
                    return this._lastTimeLogin
                },
                set: function(t) {
                    this.saveData("_lastTimeLogin", t, !1),
                    this._lastTimeLogin = t
                },
                enumerable: !1,
                configurable: !0
            }),
            Object.defineProperty(t.prototype, "stamina", {
                get: function() {
                    return this._stamina
                },
                set: function(t) {
                    t > o.default.config.gameInfo.maxStamina ? this._stamina = o.default.config.gameInfo.maxStamina : this._stamina = t,
                    this.saveData("_stamina", this._stamina)
                },
                enumerable: !1,
                configurable: !0
            }),
            Object.defineProperty(t.prototype, "checkpoint", {
                get: function() {
                    return this._checkpoint
                },
                set: function(t) {
                    this.saveData("_checkpoint", t),
                    this._checkpoint = t
                },
                enumerable: !1,
                configurable: !0
            }),
            Object.defineProperty(t.prototype, "gold", {
                get: function() {
                    return this._gold
                },
                set: function(t) {
                    this.saveData("_gold", t),
                    this._gold = t
                },
                enumerable: !1,
                configurable: !0
            }),
            Object.defineProperty(t.prototype, "diamond", {
                get: function() {
                    return this._diamond
                },
                set: function(t) {
                    this.saveData("_diamond", t),
                    this._diamond = t
                },
                enumerable: !1,
                configurable: !0
            }),
            Object.defineProperty(t.prototype, "user_code", {
                get: function() {
                    return this._user_code
                },
                set: function(t) {
                    this.saveData("_user_codes", t, !1),
                    this._user_code = t
                },
                enumerable: !1,
                configurable: !0
            }),
            Object.defineProperty(t.prototype, "openId", {
                get: function() {
                    return this._openId
                },
                set: function(t) {
                    this.saveData("_openId", t, !1),
                    this._openId = t
                },
                enumerable: !1,
                configurable: !0
            }),
            t.prototype.saveData = function(t, e, n) {
                void 0 === n && (n = !0),
                t = "100065642" + t,
                e instanceof Map ? localStorage.setItem(t, this._mapToJson(e)) : localStorage.setItem(t, JSON.stringify(e))
            }
            ,
            t.prototype.getData = function(t) {
                var e = "100065642" + t
                  , n = !0
                  , o = localStorage.getItem(e);
                return null == o || "" == o || null == o ? (n = !1,
                void this.saveData(t, this[t], !1)) : (this[t]instanceof Map ? this[t] = this._jsonToMap(o) : this[t] = JSON.parse(o),
                n)
            }
            ,
            t.prototype._strMapToObj = function(t) {
                var e = Object.create(null);
                return t.forEach(function(t, n) {
                    e[n] = t
                }),
                e
            }
            ,
            t.prototype._mapToJson = function(t) {
                return JSON.stringify(this._strMapToObj(t))
            }
            ,
            t.prototype._objToStrMap = function(t) {
                for (var e = new Map, n = 0, o = Object.keys(t); n < o.length; n++) {
                    var i = o[n];
                    e.set(i, t[i])
                }
                return e
            }
            ,
            t.prototype._jsonToMap = function(t) {
                return this._objToStrMap(JSON.parse(t))
            }
            ,
            t
        }();
        n.default = new i,
        cc._RF.pop()
    }
    , {
        "../Global": "Global"
    }],
    Constant: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "7ecf25dSYJE8p7160kC1rXT", "Constant"),
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var o = function() {
            function t() {}
            return t.REWARDED_VIDEO_END_TYPE = {
                END: 1,
                NOT_END: 2,
                ERROR: 3,
                INSERT_SCREEN: 4,
                SHARE: 5
            },
            t.UNLOCK_TYPE = {
                PASS_GAME: 1,
                TIPS: 2,
                GET_STRENGTH: 3,
                DOUBLE_STRENGTH: 4,
                NEXT_LEVEL: 5,
                GET_GOLD: 6,
                DOUBLE: 7,
                TRY_SKIN: 8
            },
            t.GAME_BOX_TWEEN_TYPE = {
                SHAKE_STOP: 1,
                SHAKE_FOREVER: 2,
                SCALE_MOVE: 3
            },
            t.LOGIN_CODE = {
                1: "\u672c\u5730\u73af\u5883\u4e0d\u4e88\u540e\u53f0\u8fdb\u884c\u4ea4\u4e92;",
                2: "\u5fae\u4fe1\u63a5\u53e3\u8c03\u7528\u5931\u8d25;",
                3: "\u5fae\u4fe1\u767b\u5f55\u63a5\u53e3\u8c03\u7528\u6210\u529f\uff0c\u4f46 Code \u4e3a\u7a7a;",
                4: "\u7528\u6237\u540e\u53f0\u767b\u5f55\u5931\u8d25 code != 200;",
                5: "\u7528\u6237\u540e\u53f0\u767b\u5f55\u5931\u8d25\uff01",
                6: "\u66f4\u65b0\u7528\u6237\u6570\u636e\u5931\u8d25, Code != 200;",
                7: "\u66f4\u65b0\u7528\u6237\u6570\u636e\u5931\u8d25;",
                8: "\u83b7\u53d6\u6e38\u620f\u914d\u7f6e\u5931\u8d25, Code != 200;",
                9: "\u83b7\u53d6\u6e38\u620f\u914d\u7f6e\u5931\u8d25;",
                10: "\u83b7\u53d6\u6e38\u620f\u5bfc\u51fa\u5931\u8d25, Code != 200;",
                11: "\u83b7\u53d6\u6e38\u620f\u5bfc\u51fa\u5931\u8d25;",
                12: "\u4e0a\u62a5\u6e38\u620f\u5bfc\u51fa\u5931\u8d25, Code != 200;",
                13: "\u4e0a\u62a5\u6e38\u620f\u5bfc\u51fa\u5931\u8d25;"
            },
            t.VIDEO_TYPE = {
                POP: 1,
                GET_PROPS: 2,
                GET_POWER: 3,
                GET_GOLD: 4,
                GET_DOUBLE: 5,
                UNLOCK: 6,
                GET_SKIN: 7,
                ENFORCE: 8,
                PLAY_END: 9,
                PLAY_CLOSE: 10
            },
            t.BOTTOM_TYPE = {
                NEW_BANNER_SHOW: 1,
                OLD_BANNER_SHOW: 2,
                NEW_CUSTOM_SHOW: 3,
                OLD_CUSTOM_SHOW: 4
            },
            t.EXPORT_TYPE = {
                FORCE: 1,
                RAND_FORCE: 2,
                GAME_BOX_ONE: 3,
                GAME_BOX_TWO: 5,
                GAME_BOX_THREE: 6,
                GAME_BOX_SLIDER: 7,
                OPEN_DATA: 8,
                VIEW_BOX: 9,
                BANNER_BOX: 10
            },
            t
        }();
        n.default = o,
        cc._RF.pop()
    }
    , {}],
    EmitBase: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "cc12a8YU1VB+aC5ouotezi4", "EmitBase");
        var o, i = this && this.__extends || (o = function(t, e) {
            return (o = Object.setPrototypeOf || {
                __proto__: []
            }instanceof Array && function(t, e) {
                t.__proto__ = e
            }
            || function(t, e) {
                for (var n in e)
                    Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n])
            }
            )(t, e)
        }
        ,
        function(t, e) {
            function n() {
                this.constructor = t
            }
            o(t, e),
            t.prototype = null === e ? Object.create(e) : (n.prototype = e.prototype,
            new n)
        }
        );
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var a = t("../GameLogMgr")
          , r = function(t) {
            function e() {
                return null !== t && t.apply(this, arguments) || this
            }
            return i(e, t),
            e.prototype.on = function(e, n, o, i) {
                if ((e || 0 == e) && o)
                    return "string" == typeof e ? t.prototype.on.call(this, e, n, o, i) : t.prototype.on.call(this, e.toString(), n, o, i);
                a.default.error("\u4e8b\u4ef6\u5bf9\u8c61|\u7c7b\u578b\u4e3a\u7a7a===> type = ", e, "target =", o)
            }
            ,
            e.prototype.once = function(e, n, o) {
                if (e && o)
                    return "string" == typeof e ? t.prototype.once.call(this, e, n, o) : t.prototype.once.call(this, e.toString(), n, o);
                a.default.error("\u4e8b\u4ef6\u5bf9\u8c61|\u7c7b\u578b\u4e3a\u7a7a===> type = ", e, "target =", o)
            }
            ,
            e.prototype.off = function(e, n, o) {
                if (e && o)
                    return "string" == typeof e ? t.prototype.off.call(this, e, n, o) : t.prototype.off.call(this, e.toString(), n, o);
                a.default.error("\u4e8b\u4ef6\u5bf9\u8c61|\u7c7b\u578b\u4e3a\u7a7a===> type = ", e, "target =", o)
            }
            ,
            e.prototype.targetOff = function(e) {
                if (e)
                    return t.prototype.targetOff.call(this, e);
                a.default.error("\u4e8b\u4ef6\u5bf9\u8c61===>  target =", e)
            }
            ,
            e.prototype.emit = function(e, n, o, i, a, r) {
                return "string" == typeof e ? t.prototype.emit.call(this, e, n, o, i, a, r) : t.prototype.emit.call(this, e.toString(), n, o, i, a, r)
            }
            ,
            e
        }(cc.EventTarget);
        n.default = r,
        cc._RF.pop()
    }
    , {
        "../GameLogMgr": "GameLogMgr"
    }],
    EmitData: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "5a2484vEg9NCJnJkpym2+TS", "EmitData"),
        Object.defineProperty(n, "__esModule", {
            value: !0
        }),
        n.EventCode = void 0;
        var o = function() {
            function t() {}
            return t.VIEW_CLOSE = "view_close",
            t.BANNER_GRID_INIT_OK = "banner_grid_init_ok",
            t.VIEW_OPEN = "view_open",
            t.GAME_INFO = "game_info",
            t
        }();
        n.default = o,
        function(t) {
            t[t.GetConfigOver = 0] = "GetConfigOver",
            t[t.PanelMgrInitOK = 1] = "PanelMgrInitOK",
            t[t.BannerBoxInitOver = 2] = "BannerBoxInitOver",
            t[t.BannerOrGridInitOK = 3] = "BannerOrGridInitOK"
        }(n.EventCode || (n.EventCode = {})),
        cc._RF.pop()
    }
    , {}],
    Emit: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "8d5e4GincFNvokwnifcxxO2", "Emit");
        var o, i = this && this.__extends || (o = function(t, e) {
            return (o = Object.setPrototypeOf || {
                __proto__: []
            }instanceof Array && function(t, e) {
                t.__proto__ = e
            }
            || function(t, e) {
                for (var n in e)
                    Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n])
            }
            )(t, e)
        }
        ,
        function(t, e) {
            function n() {
                this.constructor = t
            }
            o(t, e),
            t.prototype = null === e ? Object.create(e) : (n.prototype = e.prototype,
            new n)
        }
        );
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var a = t("./EmitBase")
          , r = function(t) {
            function e() {
                var n = t.call(this) || this;
                return e._instance ? n : (e._instance = n,
                n)
            }
            return i(e, t),
            e.instance = function() {
                return this._instance
            }
            ,
            e._instance = new a.default,
            e
        }(a.default);
        n.default = r,
        cc._RF.pop()
    }
    , {
        "./EmitBase": "EmitBase"
    }],
    EndView: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "67db6+pXehLwYlpeSPIChqs", "EndView");
        var o, i = this && this.__extends || (o = function(t, e) {
            return (o = Object.setPrototypeOf || {
                __proto__: []
            }instanceof Array && function(t, e) {
                t.__proto__ = e
            }
            || function(t, e) {
                for (var n in e)
                    Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n])
            }
            )(t, e)
        }
        ,
        function(t, e) {
            function n() {
                this.constructor = t
            }
            o(t, e),
            t.prototype = null === e ? Object.create(e) : (n.prototype = e.prototype,
            new n)
        }
        ), a = this && this.__decorate || function(t, e, n, o) {
            var i, a = arguments.length, r = a < 3 ? e : null === o ? o = Object.getOwnPropertyDescriptor(e, n) : o;
            if ("object" == typeof Reflect && "function" == typeof Reflect.decorate)
                r = Reflect.decorate(t, e, n, o);
            else
                for (var c = t.length - 1; c >= 0; c--)
                    (i = t[c]) && (r = (a < 3 ? i(r) : a > 3 ? i(e, n, r) : i(e, n)) || r);
            return a > 3 && r && Object.defineProperty(e, n, r),
            r
        }
        ;
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var r = t("./HomeView")
          , c = t("../../Common/manage/Layer/LayerPanel")
          , s = t("../../Common/Tools")
          , l = t("../../Common/manage/PanelMgr")
          , u = t("./GameView")
          , d = t("../../Common/manage/LoadMgr")
          , h = t("../../Common/manage/CacheMgr")
          , f = t("../Game/gameConfig")
          , p = t("../../Common/manage/AudioMgr")
          , m = t("../../Common/Constant")
          , g = cc._decorator
          , _ = g.ccclass
          , y = (g.property,
        function(t) {
            function e() {
                var e = null !== t && t.apply(this, arguments) || this;
                return e.coinNode = null,
                e.btn_adv = null,
                e.btn_back = null,
                e.btn_share = null,
                e.btn_next = null,
                e.coinCount = 0,
                e.coinLabel = null,
                e.coinPool = null,
                e.coinPrefab = null,
                e.coinIcon = null,
                e.startPos = null,
                e.endPos = null,
                e.isClick = !1,
                e.starSkeleton = null,
                e.starDragon = null,
                e.bgNode = null,
                e
            }
            var n;
            return i(e, t),
            n = e,
            e.getUrl = function() {
                return {
                    bundle: "endView",
                    name: "endView"
                }
            }
            ,
            e.prototype.hide = function() {}
            ,
            e.prototype.initUI = function() {
                var t = this;
                this.coinNode = this.getNode("result/bg/coin"),
                this.btn_adv = this.getNode("result/bg/btn_adv"),
                this.btn_back = this.getNode("result/bg/btn_back"),
                this.btn_share = this.getNode("result/bg/btn_share"),
                this.btn_next = this.getNode("result/bg/btn_next"),
                this.coinLabel = this.coinNode.getComponent(cc.Label),
                this.coinIcon = this.getNode("coinIcon"),
                this.starSkeleton = this.getNode("starSkeleton"),
                this.bgNode = this.getNode("result/bg"),
                this.starDragon = this.starSkeleton.getComponent(dragonBones.ArmatureDisplay),
                this.coinPool = new cc.NodePool,
                d.default.loadPrefab("game/coin").then(function(e) {
                    t.coinPrefab = e
                })
            }
            ,
            e.prototype.show = function(t) {
                t.isWin && (h.default.checkpoint = h.default.checkpoint + 1),
                this.initEnd(t),
                f.default.isDoubleTime = !1
            }
            ,
            e.prototype.initEnd = function(t) {
                var e = this.btn_adv.getComponent(cc.Sprite)
                  , n = this.btn_next.getComponent(cc.Sprite)
                  , o = this.bgNode.getComponent(cc.Sprite)
                  , i = cc.director.getScene().children[0].getChildByName("gameInfoLayer").getChildByName("gameInfoView").getChildByName("gold").getPosition()
                  , a = this.coinIcon.getPosition();
                this.startPos = a,
                this.endPos = i;
                var r = t.count;
                t.isWin ? (d.default.loadSprite(o, "endView/win").then(),
                d.default.loadSprite(e, "endView/btn_2", d.default.getBundle("sub"), !1).then(),
                d.default.loadSprite(n, "endView/btn_3").then(),
                this.coinLabel.string = "0",
                this.starDragon.armatureName = "xinxin" + r,
                this.coinCount = s.default.getRandom(f.default.extra * r, f.default.extra * (r + 1)),
                this.getCoin(),
                this.onTouch(this.btn_adv, this.handle_double),
                this.onTouch(this.btn_back, this.handle_back),
                this.onTouch(this.btn_next, this.handle_next)) : (this.isClick = !0,
                d.default.loadSprite(o, "endView/lose").then(),
                d.default.loadSprite(e, "endView/btn_1", d.default.getBundle("sub"), !1).then(),
                d.default.loadSprite(n, "endView/btn").then(),
                this.coinLabel.string = "0",
                this.starDragon.armatureName = "xinxin0",
                this.onTouch(this.btn_adv, this.handle_time),
                this.onTouch(this.btn_back, this.handle_back),
                this.onTouch(this.btn_next, this.handle_next))
            }
            ,
            e.prototype.handle_double = function() {
                var t = this;
                this.isClick = !1,
                s.default.handleVideo(m.default.VIDEO_TYPE.GET_DOUBLE).then(function(e) {
                    e ? (t.flyCoin(t.startPos, t.endPos),
                    t.btn_adv.active = !1) : t.isClick = !0
                })
            }
            ,
            e.prototype.handle_time = function() {
                s.default.handleVideo(m.default.VIDEO_TYPE.GET_DOUBLE).then(function(t) {
                    t && (f.default.isDoubleTime = !0,
                    l.default.INS.openPanel({
                        layer: l.Layer.gameLayer,
                        panel: u.default,
                        call: function() {
                            l.default.INS.closePanel(n)
                        }
                    }))
                })
            }
            ,
            e.prototype.handle_back = function() {
				show_nativead()
                this.isClick && l.default.INS.openPanel({
                    layer: l.Layer.gameLayer,
                    panel: r.default,
                    call: function() {
                        l.default.INS.closePanel(n)
                    }
                })
            }
            ,
            e.prototype.handle_next = function() {
				show_nativead()
                this.isClick && s.default.changeStamina(-1) && l.default.INS.openPanel({
                    layer: l.Layer.gameLayer,
                    panel: r.default,
                    call: function() {
                        l.default.INS.closePanel(n)
                    }
                })
            }
            ,
            e.prototype.getCoin = function() {
                var t = this;
                this.changeCoins(this.coinCount),
                this.scheduleOnce(function() {
                    t.flyCoin(t.startPos, t.endPos)
                }, f.default.coinsChangeTime)
            }
            ,
            e.prototype.changeCoins = function(t) {
                for (var e = this, n = f.default.coinsChangeTime / t, o = 0, i = function(t) {
                    window.setTimeout(function() {
                        e.coinLabel.string = t.toString()
                    }, 1e3 * o),
                    o += n
                }, a = 0; a < t; a++)
                    i(a)
            }
            ,
            e.prototype.initPool = function(t) {
                void 0 === t && (t = 20);
                for (var e = 0; e < t; e++) {
                    var n = cc.instantiate(this.coinPrefab);
                    this.coinPool.put(n)
                }
            }
            ,
            e.prototype.flyCoin = function(t, e, n) {
                var o = 15 * Math.random() + 10;
                this.playCoinFlyAnim(o, t, e, n)
            }
            ,
            e.prototype.playCoinFlyAnim = function(t, e, n, o, i) {
                var a = this;
                void 0 === i && (i = 130);
                var r = this.coinPool.size()
                  , c = r > t ? 0 : t - r;
                this.initPool(c);
                var l = this.getCirclePoints(i, e, t).map(function(t) {
                    var o = a.coinPool.get();
                    return o.setPosition(e),
                    a.node.addChild(o),
                    {
                        node: o,
                        stPos: e,
                        mdPos: t,
                        edPos: n,
                        dis: t.sub(n).mag()
                    }
                });
                l = l.sort(function(t, e) {
                    return t.dis - e.dis > 0 ? 1 : t.dis - e.dis < 0 ? -1 : 0
                });
                var u = !1;
                l.forEach(function(t, e) {
                    var n;
                    n = 0 == e % 2 ? 300 : -300,
                    cc.tween(t.node).to(.3, {
                        x: t.mdPos.x,
                        y: t.mdPos.y
                    }).call(function() {
                        u || (u = !0)
                    }).delay(.05 * e).bezierTo(.5, cc.v2(n, 300), cc.v2(n, 300), cc.v2(t.edPos.x, t.edPos.y)).call(function() {
                        if (a.coinPool.put(t.node),
                        p.default.play("getCoin").then(),
                        e == l.length - 1 && (s.default.changeGold(a.coinCount),
                        a.isClick = !0,
                        o)) {
                            var n = a;
                            o.call(n)
                        }
                    }).start()
                })
            }
            ,
            e.prototype.getCirclePoints = function(t, e, n, o) {
                void 0 === o && (o = 60);
                for (var i = [], a = Math.PI / 180 * Math.round(360 / n), r = 0; r < n; r++) {
                    var c = e.x + t * Math.sin(a * r)
                      , s = e.y + t * Math.cos(a * r);
                    i.unshift(cc.v3(c + Math.random() * o, s + Math.random() * o, 0))
                }
                return i
            }
            ,
            n = a([_], e)
        }(c.default));
        n.default = y,
        cc._RF.pop()
    }
    , {
        "../../Common/Constant": "Constant",
        "../../Common/Tools": "Tools",
        "../../Common/manage/AudioMgr": "AudioMgr",
        "../../Common/manage/CacheMgr": "CacheMgr",
        "../../Common/manage/Layer/LayerPanel": "LayerPanel",
        "../../Common/manage/LoadMgr": "LoadMgr",
        "../../Common/manage/PanelMgr": "PanelMgr",
        "../Game/gameConfig": "gameConfig",
        "./GameView": "GameView",
        "./HomeView": "HomeView"
    }],
    GameInfoView: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "f41d6l50UdLtJkX2F0iP7iE", "GameInfoView");
        var o, i = this && this.__extends || (o = function(t, e) {
            return (o = Object.setPrototypeOf || {
                __proto__: []
            }instanceof Array && function(t, e) {
                t.__proto__ = e
            }
            || function(t, e) {
                for (var n in e)
                    Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n])
            }
            )(t, e)
        }
        ,
        function(t, e) {
            function n() {
                this.constructor = t
            }
            o(t, e),
            t.prototype = null === e ? Object.create(e) : (n.prototype = e.prototype,
            new n)
        }
        ), a = this && this.__decorate || function(t, e, n, o) {
            var i, a = arguments.length, r = a < 3 ? e : null === o ? o = Object.getOwnPropertyDescriptor(e, n) : o;
            if ("object" == typeof Reflect && "function" == typeof Reflect.decorate)
                r = Reflect.decorate(t, e, n, o);
            else
                for (var c = t.length - 1; c >= 0; c--)
                    (i = t[c]) && (r = (a < 3 ? i(r) : a > 3 ? i(e, n, r) : i(e, n)) || r);
            return a > 3 && r && Object.defineProperty(e, n, r),
            r
        }
        ;
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var r = t("../../Common/manage/CacheMgr")
          , c = t("../../Common/manage/Layer/LayerPanel")
          , s = t("../../Common/Global")
          , l = t("./ShortageView")
          , u = t("../../Common/manage/PanelMgr")
          , d = cc._decorator.ccclass
          , h = function(t) {
            function e() {
                var e = null !== t && t.apply(this, arguments) || this;
                return e.gold = null,
                e.stamina = null,
                e.diamond = null,
                e.gold_add_button = null,
                e.stamina_add_button = null,
                e.residue_node = null,
                e.residue_sprite = null,
                e.animationTime = null,
                e.gold_num = null,
                e.stamina_num = 0,
                e.diamond_num = 0,
                e.timeouts = new Map,
                e.stamina_minute = 0,
                e.stamina_second = 0,
                e
            }
            var n;
            return i(e, t),
            n = e,
            e.getUrl = function() {
                return {
                    bundle: "gameInfoView",
                    name: "gameInfoView"
                }
            }
            ,
            e.INS = function() {
                return this.gameInfoViewIns
            }
            ,
            e.prototype.initUI = function() {
                n.gameInfoViewIns = this,
                this.gold_num = r.default.gold,
                this.stamina_num = r.default.stamina,
                this.diamond_num = r.default.diamond,
                this.animationTime = s.default.config.gameInfo.animation,
                this.gold = this.getNode("gold/num"),
                this.stamina = this.getNode("stamina/num"),
                this.gold_add_button = this.getNode("gold/add"),
                this.stamina_add_button = this.getNode("stamina/add"),
                this.residue_node = this.getNode("stamina/time"),
                this.residue_sprite = this.getNode("stamina/man"),
                this.timeouts.set("gold", []),
                this.timeouts.set("stamina", []),
                this.timeouts.set("diamond", [])
            }
            ,
            e.prototype.show = function() {
                this.gold.getComponent(cc.Label).string = this.gold_num.toString(),
                this.stamina.getComponent(cc.Label).string = this.stamina_num.toString(),
                this.onTouch(this.gold_add_button, function() {
                    u.default.INS.openPanel({
                        panel: l.default,
                        layer: u.Layer.gameLayer,
                        param: {
                            type: "gold"
                        }
                    })
                }),
                this.onTouch(this.stamina_add_button, function() {
                    u.default.INS.openPanel({
                        panel: l.default,
                        layer: u.Layer.gameLayer,
                        param: {
                            type: "stamina"
                        }
                    })
                })
            }
            ,
            e.prototype.hide = function() {}
            ,
            e.prototype.update = function() {
                var t = r.default.gold;
                this.gold_num != t && this.changeAnimation("gold", t - this.gold_num);
                var e = r.default.stamina;
                if (this.stamina_num != e && this.changeAnimation("stamina", e - this.stamina_num),
                r.default.stamina >= s.default.config.gameInfo.maxStamina)
                    return this.residue_node.active = !1,
                    this.residue_sprite.active = !0,
                    this.stamina_minute = 0,
                    void (this.stamina_second = 0);
                this.residue_sprite.active = !1,
                this.residue_node.active = !0,
                this.residue_node.getComponent(cc.Label).string = this.stamina_minute.toString().padStart(2, "0") + ":" + this.stamina_second.toString().padStart(2, "0")
            }
            ,
            e.prototype.changeResidue = function(t, e) {
                this.stamina_minute = t,
                this.stamina_second = e
            }
            ,
            e.prototype.changeAnimation = function(t, e) {
                var n = this;
                this.clearTimeOut(t);
                var o = Math.abs(e)
                  , i = this.animationTime / o
                  , a = 0
                  , r = this[t + "_num"];
                this[t + "_num"] += e;
                for (var c = function(o) {
                    e < 0 ? (s.timeouts.get(t)[o] = window.setTimeout(function() {
                        n[t].getComponent(cc.Label).string = (r - o).toString()
                    }, 1e3 * a),
                    a += i) : (s.timeouts.get(t)[o] = window.setTimeout(function() {
                        n[t].getComponent(cc.Label).string = (r + o).toString()
                    }, 1e3 * a),
                    a += i)
                }, s = this, l = 1; l <= o; l++)
                    c(l)
            }
            ,
            e.prototype.clearTimeOut = function(t) {
                for (var e = this.timeouts.get(t), n = 0; n < e.length; n++)
                    this.timeouts[n] && window.clearTimeout(e[n]);
                this[t].getComponent(cc.Label).string = this[t + "_num"].toString(),
                this.timeouts.set(t, [])
            }
            ,
            e.gameInfoViewIns = null,
            n = a([d], e)
        }(c.default);
        n.default = h,
        cc._RF.pop()
    }
    , {
        "../../Common/Global": "Global",
        "../../Common/manage/CacheMgr": "CacheMgr",
        "../../Common/manage/Layer/LayerPanel": "LayerPanel",
        "../../Common/manage/PanelMgr": "PanelMgr",
        "./ShortageView": "ShortageView"
    }],
    GameLogMgr: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "84d83ziGZZN4LH30WDE1S/l", "GameLogMgr"),
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var o = t("../Global")
          , i = function() {
            function t() {}
            return t.log = function() {
                for (var t = [], e = 0; e < arguments.length; e++)
                    t[e] = arguments[e];
                o.default.IS_LOG && console.log.apply(cc.log, t)
            }
            ,
            t.warn = function() {
                for (var t = [], e = 0; e < arguments.length; e++)
                    t[e] = arguments[e];
                o.default.IS_LOG && console.warn.apply(cc.warn, t)
            }
            ,
            t.error = function() {
                for (var t = [], e = 0; e < arguments.length; e++)
                    t[e] = arguments[e];
                o.default.IS_LOG && console.error.apply(cc.error, t)
            }
            ,
            t
        }();
        n.default = i,
        cc._RF.pop()
    }
    , {
        "../Global": "Global"
    }],
    GameView: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "bfce93ObH9DBbRXeAjfXK5B", "GameView");
        var o, i = this && this.__extends || (o = function(t, e) {
            return (o = Object.setPrototypeOf || {
                __proto__: []
            }instanceof Array && function(t, e) {
                t.__proto__ = e
            }
            || function(t, e) {
                for (var n in e)
                    Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n])
            }
            )(t, e)
        }
        ,
        function(t, e) {
            function n() {
                this.constructor = t
            }
            o(t, e),
            t.prototype = null === e ? Object.create(e) : (n.prototype = e.prototype,
            new n)
        }
        ), a = this && this.__decorate || function(t, e, n, o) {
            var i, a = arguments.length, r = a < 3 ? e : null === o ? o = Object.getOwnPropertyDescriptor(e, n) : o;
            if ("object" == typeof Reflect && "function" == typeof Reflect.decorate)
                r = Reflect.decorate(t, e, n, o);
            else
                for (var c = t.length - 1; c >= 0; c--)
                    (i = t[c]) && (r = (a < 3 ? i(r) : a > 3 ? i(e, n, r) : i(e, n)) || r);
            return a > 3 && r && Object.defineProperty(e, n, r),
            r
        }
        ;
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var r = t("../../Common/manage/Layer/LayerPanel")
          , c = t("../../Common/manage/PanelMgr")
          , s = t("../../Common/manage/CacheMgr")
          , l = t("../Game/gameConfig")
          , u = t("../../Common/manage/LoadMgr")
          , d = t("../../Common/manage/AudioMgr")
          , h = t("../Game/Item")
          , f = t("../../Common/Tools")
          , p = t("../../Common/Constant")
          , m = t("./HomeView")
          , g = t("./EndView")
          , _ = t("../../Common/manage/Tips/ToastTips")
          , y = cc._decorator
          , v = y.ccclass
          , b = (y.property,
        function(t) {
            function e() {
                var e = null !== t && t.apply(this, arguments) || this;
                return e.majing = null,
                e.withdraw = null,
                e.withdrawCountNode = null,
                e.withdrawVideoNode = null,
                e.hint = null,
                e.hintCountNode = null,
                e.hintVideoNode = null,
                e.replay = null,
                e.gameContent = null,
                e.resultData = [],
                e.resultList = null,
                e.resultNodeList = [],
                e.tempAction = null,
                e.nowTime = null,
                e.animation = new Map,
                e.checkPoint = null,
                e.levelInfo = null,
                e.progressBar = null,
                e.barSprite = null,
                e.allTime = null,
                e.tempTime = null,
                e.star1Node = null,
                e.star2Node = null,
                e.star3Node = null,
                e.starCount = null,
                e.btn_pause = null,
                e.is_pause = !0,
                e.btn_close = null,
                e.btn_continue = null,
                e.btn_again = null,
                e.btn_home = null,
                e.pauseNode = null,
                e.blackNode = null,
                e.rookieNode = null,
                e.handNode = null,
                e.itemMask = null,
                e.count = 0,
                e.winSkeleton = null,
                e.winPlist = null,
                e.hintHand = null,
                e.hintCount = 0,
                e.tempHand = null,
                e.tempCheckPoint = null,
                e.isOver = !1,
                e
            }
            var n;
            return i(e, t),
            n = e,
            e.getUrl = function() {
                return {
                    bundle: "gameView",
                    name: "gameView"
                }
            }
            ,
            e.prototype.initUI = function() {
                var t = this;
                this.checkPoint = s.default.checkpoint,
                this.checkPoint <= 0 && (s.default.checkpoint = 1,
                this.checkPoint = 1),
                this.checkPoint % l.default.allLevel == 0 ? this.tempCheckPoint = l.default.allLevel : this.tempCheckPoint = this.checkPoint % l.default.allLevel,
                this.majing = this.getNode("\u9ebb\u5c06"),
                this.majing.active = !1,
                l.default.all_item_position = [],
                this.withdraw = this.getNode("downMenu/withdraw"),
                this.withdrawCountNode = this.withdraw.getChildByName("count"),
                this.withdrawVideoNode = this.withdraw.getChildByName("video"),
                this.hint = this.getNode("downMenu/hint"),
                this.hintCountNode = this.hint.getChildByName("count"),
                this.hintVideoNode = this.hint.getChildByName("video"),
                this.replay = this.getNode("downMenu/replay"),
                this.gameContent = this.getNode("gameContent"),
                this.resultList = this.getNode("resultList"),
                this.levelInfo = this.getNode("levelInfo"),
                this.levelInfo.getComponent(cc.Label).string = "\u5173\u5361 - " + this.checkPoint,
                this.progressBar = this.getNode("progress/bar"),
                this.barSprite = this.progressBar.getComponent(cc.Sprite),
                this.star1Node = this.getNode("progress/star1/star"),
                this.star2Node = this.getNode("progress/star2/star"),
                this.star3Node = this.getNode("progress/star3/star"),
                this.btn_pause = this.getNode("pause"),
                this.btn_close = this.getNode("pauseParent/close"),
                this.btn_continue = this.getNode("pauseParent/continue"),
                this.btn_again = this.getNode("pauseParent/again"),
                this.btn_home = this.getNode("pauseParent/home"),
                this.pauseNode = this.getNode("pauseParent"),
                this.blackNode = this.getNode("black"),
                this.rookieNode = this.getNode("rookie"),
                this.handNode = this.getNode("hand"),
                this.itemMask = this.getNode("itemMask"),
                this.winSkeleton = this.getNode("winSkeleton"),
                this.winPlist = this.getNode("winPlist"),
                this.winPlist.active = !1,
                this.winSkeleton.active = !1,
                this.blackNode.active = !1,
                this.rookieNode.active = !1,
                this.pauseNode.active = !1,
                this.handNode.active = !1,
                this.itemMask.active = !1,
                this.starCount = 3,
                this.onTouch(this.withdraw, this.handle_withdraw),
                this.onTouch(this.hint, this.handle_hint),
                this.onTouch(this.replay, this.handle_replay),
                this.onTouch(this.btn_pause, this.handle_pause),
                this.onTouch(this.btn_close, this.handle_continue),
                this.onTouch(this.btn_continue, this.handle_continue),
                this.onTouch(this.btn_again, this.handle_again),
                this.onTouch(this.btn_home, this.handle_home),
                l.default.isDoubleTime ? this.allTime = 2 * l.default.level_all_time : this.allTime = l.default.level_all_time,
                this.tempTime = this.allTime,
                u.default.loadPrefab("game/hand").then(function(e) {
                    t.hintHand = cc.instantiate(e)
                })
            }
            ,
            e.prototype.show = function() {
                this.initGame(),
                this.initProp()
            }
            ,
            e.prototype.initProp = function() {
                var t = s.default.recall
                  , e = s.default.hint;
                t <= 0 ? (this.withdrawVideoNode.active = !0,
                this.withdrawCountNode.active = !1) : (this.withdrawVideoNode.active = !1,
                this.withdrawCountNode.active = !0,
                this.withdrawCountNode.getChildByName("label").getComponent(cc.Label).string = t + ""),
                e <= 0 ? (this.hintVideoNode.active = !0,
                this.hintCountNode.active = !1) : (this.hintVideoNode.active = !1,
                this.hintCountNode.active = !0,
                this.hintCountNode.getChildByName("label").getComponent(cc.Label).string = e + "")
            }
            ,
            e.prototype.initGame = function() {
                var t = this;
                this.is_pause = !0;
                for (var e = l.default.width_max_num; e > 0; e--) {
                    var n = e * this.majing.width
                      , o = (e + 1) * this.majing.height
                      , i = new cc.Rect(0,0,n,o)
                      , a = f.default.getNodeForRect(i);
                    a.setAnchorPoint(.5, .5),
                    a.setPosition(0, 0),
                    a.name = e + "",
                    this.gameContent.addChild(a);
                    for (var r = [], c = {
                        x: 0 - n / 2,
                        y: o / 2
                    }, s = void 0, d = void 0, p = 0; p < e + 1; p++) {
                        s = 0 == p ? c.y - this.majing.height / 2 : c.y - this.majing.height / 2 - p * this.majing.height + 11 * p;
                        for (var m = 0; m < e; m++)
                            d = 0 == m ? c.x + this.majing.width / 2 : c.x + this.majing.width / 2 + m * this.majing.width - 11 * m,
                            r.push({
                                pointX: d,
                                pointY: s,
                                orderBy: e
                            })
                    }
                    l.default.all_item_position.push(r)
                }
                for (var g = l.default.level_data2[this.tempCheckPoint - 1], _ = null, y = this.randomCount(), v = g.limit, b = new Array, C = 0; C < v; C++)
                    b.push(y[C] + 1);
                for (var w = new Array, P = 0, N = 0, L = 0; L < g.data.length; L++) {
                    for (var O = new Array, M = 0; M < g.data[L].length; M++)
                        0 != g.data[L][M] && (N >= 3 && (N = 0,
                        P++),
                        P > b.length - 1 && (P = 0),
                        O.push(b[P]),
                        N++);
                    1 != this.tempCheckPoint && O.sort(function() {
                        return .5 - Math.random()
                    }),
                    w.push(O)
                }
                for (var T = 0; T < g.tier.length; T++) {
                    var I = 0;
                    _ = this.gameContent.getChildByName(g.tier[T] + "");
                    for (var S = function(t) {
                        if (0 == g.data[T][t])
                            return "continue";
                        var e = w[T][I]
                          , n = cc.instantiate(E.majing);
                        _.addChild(n),
                        n.active = !0;
                        var o = n.getComponent(h.default);
                        o.itemData = e;
                        var i = l.default.width_max_num - g.tier[T]
                          , a = l.default.all_item_position[i][t];
                        n.setPosition(a.pointX, a.pointY);
                        var r = n.getComponent(cc.Sprite);
                        u.default.loadAtlas("gameView/fruits").then(function(t) {
                            var n = t.getSpriteFrame(e);
                            r.spriteFrame = n
                        }),
                        n.name = a.orderBy + "-" + t,
                        o.whereItem = t,
                        o.hierarchy = a.orderBy,
                        n.getChildByName("mask").active = !1,
                        o.isClick = !1,
                        E.onTouch(n, E.handle_majiang, "clickItem"),
                        I++
                    }, E = this, D = 0; D < g.data[T].length; D++)
                        S(D)
                }
                var R, j, A = [];
                for (T = g.tier.length - 1; T >= 0; T--)
                    R = g.tier[T],
                    j = this.gameContent.getChildByName(R + ""),
                    A.push(j);
                for (var k = 0, B = function(e) {
                    e % 2 == 0 ? A[e].x -= 800 : A[e].x += 800,
                    cc.tween(A[e]).delay(k).to(.5, {
                        x: 40
                    }, {
                        easing: "bounceOut"
                    }).call(function() {
                        if (1 == t.checkPoint) {
                            t.blackNode.active = !0,
                            t.rookieNode.active = !0,
                            t.is_pause = !0;
                            for (var n = function(n) {
                                var o = A[e].children[n];
                                if (0 == n) {
                                    t.tempHand = cc.instantiate(t.hintHand),
                                    A[e].addChild(t.tempHand),
                                    t.tempHand.scale = .5,
                                    t.tempHand.setAnchorPoint(0, 1),
                                    t.tempHand.setPosition(A[e].children[t.hintCount].getPosition());
                                    var i = t.tempHand.getComponent(cc.Animation);
                                    i.schedule(function() {
                                        i.play()
                                    }, 2),
                                    t.hintCount++,
                                    "hand" != o.name && (o.getComponent(h.default).isClick = !0)
                                } else
                                    "hand" != o.name && (o.getComponent(h.default).isClick = !1)
                            }, o = 0; o < A[e].childrenCount; o++)
                                n(o)
                        } else if (e == A.length - 1) {
                            t.is_pause = !1;
                            for (var i = 0; i < A[e].childrenCount; i++)
                                A[e].children[i].getComponent(h.default).isClick = !0
                        }
                        if (0 != e)
                            for (var a = 0; a < A[e - 1].childrenCount; a++)
                                A[e - 1].children[a].getChildByName("mask").active = !0,
                                A[e - 1].children[a].getComponent(h.default).isClick = !1
                    }).start(),
                    k += .5
                }, G = 0; G < A.length; G++)
                    B(G)
            }
            ,
            e.prototype.handle_continue = function() {
				show_nativead();
                var t = this;
                cc.tween(this.pauseNode).to(.5, {
                    scale: 0
                }, {
                    easing: "backInOut"
                }).call(function() {
                    t.pauseNode.active = !1
                }).start(),
                this.is_pause = !1
            }
            ,
            e.prototype.handle_again = function() {
				show_nativead();
                var t = this;
                cc.tween(this.pauseNode).to(.5, {
                    scale: 0
                }, {
                    easing: "backInOut"
                }).call(function() {
                    t.pauseNode.active = !1
                }).start(),
                this.is_pause = !1,
                this.handle_replay()
            }
            ,
            e.prototype.handle_home = function() {
				show_nativead();
                c.default.INS.openPanel({
                    layer: c.Layer.gameLayer,
                    panel: m.default,
                    call: function() {
                        c.default.INS.closePanel(n)
                    }
                })
            }
            ,
            e.prototype.handle_cancelPause = function() {
				show_nativead();
                this.is_pause = !1
            }
            ,
            e.prototype.update = function(t) {
                this.countDown(t)
            }
            ,
            e.prototype.handle_pause = function() {
                this.is_pause = !0,
                this.pauseNode.active = !0,
                this.pauseNode.scale = 0,
                cc.tween(this.pauseNode).to(.5, {
                    scale: 1
                }, {
                    easing: "backInOut"
                }).start()
            }
            ,
            e.prototype.countDown = function(t) {
                if (!(this.is_pause || this.allTime <= 0)) {
                    this.tempTime -= t;
                    var e = this.tempTime / this.allTime;
                    this.barSprite.fillRange = e,
                    this.tempTime <= 140 ? this.star3Node.active && (this.star3Node.active = !1,
                    this.starCount--) : this.star3Node.active || (this.star3Node.active = !0,
                    this.starCount++),
                    this.tempTime <= 90 ? this.star2Node.active && (this.star2Node.active = !1,
                    this.starCount--) : this.star2Node.active || (this.star2Node.active = !0,
                    this.starCount++),
                    this.tempTime <= 30 ? this.star1Node.active && (this.star1Node.active = !1,
                    this.starCount--) : this.star1Node.active || (this.star1Node.active = !0,
                    this.starCount++)
                }
            }
            ,
            e.prototype.randomCount = function() {
                for (var t = l.default.max_Item, e = new Array, n = 0; n < t; n++)
                    e[n] = n;
                return e.sort(function() {
                    return .5 - Math.random()
                }),
                e
            }
            ,
            e.prototype.handle_majiang = function(t) {
                var e = this;
                if (t.target.getComponent(h.default).isClick && 6 != this.resultData.length) {
                    var n = t.target.getComponent(h.default).itemData;
                    if (1 == this.checkPoint) {
                        this.tempHand.getComponent(cc.Animation).unschedule(function() {}),
                        this.tempHand.destroy(),
                        this.tempHand = null;
                        var o = l.default.level_data2[0].tier[0]
                          , i = this.gameContent.getChildByName(o + "");
                        this.scheduleOnce(function() {
                            for (var t = function(t) {
                                var n = i.children[t];
                                if (i.childrenCount < e.hintCount && (e.hintCount = 1),
                                t == e.hintCount) {
                                    e.tempHand = cc.instantiate(e.hintHand),
                                    i.addChild(e.tempHand),
                                    e.tempHand.scale = .5,
                                    e.tempHand.setAnchorPoint(0, 1),
                                    e.tempHand.setPosition(i.children[e.hintCount].getPosition());
                                    var o = e.tempHand.getComponent(cc.Animation);
                                    o.schedule(function() {
                                        o.play()
                                    }, 2),
                                    "hand" != n.name && (n.getComponent(h.default).isClick = !0)
                                } else
                                    "hand" != n.name && (n.getComponent(h.default).isClick = !1)
                            }, n = 0; n < i.childrenCount; n++)
                                t(n);
                            e.hintCount++
                        }, .5)
                    }
                    for (var a = !1, r = null, c = 0; c < this.resultData.length; c++)
                        this.resultData[c] == n && (r = c + 1,
                        a = !0);
                    a ? this.resultData.splice(r, 0, n) : this.resultData.push(n),
                    this.offTouch(t.target),
                    this.flyItem(t.target, n, r),
                    this.maskItem(t.target)
                }
            }
            ,
            e.prototype.maskItem = function(t) {
                var e = t.getComponent(h.default)
                  , n = e.hierarchy
                  , o = e.whereItem
                  , i = this.gameContent.getChildByName(n + "")
                  , a = this.gameContent.getChildByName(n + 1 + "")
                  , r = t.getBoundingBoxToWorld()
                  , c = [];
                if (null != a)
                    for (var s = 0; s < a.childrenCount; s++) {
                        var l = a.children[s];
                        r.intersects(l.getBoundingBoxToWorld()) && c.push(l)
                    }
                if (c.length)
                    for (var u = c.length - 1; u >= 0; u--) {
                        var d = c[u].getBoundingBoxToWorld();
                        for (s = 0; s < i.childrenCount; s++)
                            if (o != (l = i.children[s]).getComponent(h.default).whereItem && !this.animation.get(l.uuid) && d.intersects(l.getBoundingBoxToWorld())) {
                                c.splice(u, 1);
                                break
                            }
                    }
                for (this.tempAction.maskItem = c,
                s = 0; s < c.length; s++)
                    c[s].getComponent(h.default).isClick = !0,
                    c[s].getChildByName("mask").active = !1
            }
            ,
            e.prototype.flyItem = function(t, e, n) {
                var o = null == n ? this.resultData.length : n + 1;
                if (!(o > 6)) {
                    t.getChildByName("mask").active = !1;
                    var i = this.resultList.children[o - 1]
                      , a = i.parent.convertToWorldSpaceAR(i.getPosition())
                      , r = t.parent.convertToNodeSpaceAR(a)
                      , c = t.getPosition();
                    this.resultNodeList.splice(o - 1, 0, t),
                    this.refreshNode(),
                    this.tempAction = {
                        node: t,
                        startPos: c,
                        endPos: r,
                        index: n
                    },
                    this.animation.set(t.uuid, 1)
                }
            }
            ,
            e.prototype.gameOver = function(t, e, o) {
                this.isOver || (this.isOver = !0,
                this.is_pause = !0,
                e ? (d.default.play("win").then(),
                this.winPlist.active = !0) : d.default.play("lose").then(),
                window.setTimeout(function() {
                    c.default.INS.openPanel({
                        layer: c.Layer.gameLayer,
                        panel: g.default,
                        param: {
                            residue: 1,
                            isWin: e,
                            count: o
                        },
                        call: function() {
                            c.default.INS.closePanel(n)
                        }
                    })
                }, t))
            }
            ,
            e.prototype.isRepeat = function() {
                for (var t = this, e = {}, n = 0, o = 0, i = 0; i < this.resultData.length; i++) {
                    var a = this.resultData[i];
                    if (e[a] = e[a] + 1 || 1,
                    3 == e[a]) {
                        1 == this.tempCheckPoint && (this.itemMask.active = !1,
                        this.count < 1 && (this.handNode.y += this.majing.height,
                        this.count++)),
                        d.default.play("remove", 1).then(),
                        this.tempTime += 5,
                        this.tempTime >= this.allTime && (this.tempTime = this.allTime);
                        for (var r = 0; r < this.resultData.length; r++)
                            if (this.resultData[r] == a) {
                                if (3 == n)
                                    break;
                                n++,
                                this.resultData.splice(r, 1),
                                r--
                            }
                        for (var c, s = function(e) {
                            if (l.resultNodeList[e].getComponent(h.default).itemData == a) {
                                if (3 == o)
                                    return c = e,
                                    "break";
                                o++;
                                var n = l.resultNodeList[e];
                                cc.tween(n).delay(.25).to(.1, {
                                    scale: 1.2
                                }).to(.25, {
                                    scale: 0
                                }).call(function() {
                                    n.destroy(),
                                    t.refreshNode(),
                                    t.checkWinner()
                                }).start(),
                                l.resultNodeList.splice(e, 1),
                                e--
                            }
                            c = e
                        }, l = this, u = 0; u < this.resultNodeList.length; u++) {
                            var f = s(u);
                            if (u = c,
                            "break" === f)
                                break
                        }
                    } else
                        this.resultNodeList.length >= 6 && this.checkGameOver()
                }
            }
            ,
            e.prototype.checkGameOver = function() {
                var t = this;
                setTimeout(function() {
                    t.resultData.length >= 6 && setTimeout(function() {
                        t.gameOver(0, !1, t.starCount)
                    }, 500)
                }, 500)
            }
            ,
            e.prototype.checkWinner = function() {
                var t = this;
                setTimeout(function() {
                    for (var e = !0, n = 0; n < t.gameContent.childrenCount; n++)
                        0 != t.gameContent.children[n].childrenCount && (e = !1);
                    e && (t.winSkeleton.active = !0,
                    t.gameOver(2e3, !0, t.starCount))
                }, 500)
            }
            ,
            e.prototype.refreshNode = function() {
                var t = this;
                if (this.resultNodeList.length)
                    for (var e, n = function(n) {
                        var i = o.resultList.children[n];
                        if ("" == i.name)
                            return o.resultNodeList.splice(n, 1),
                            e = --n,
                            "continue";
                        var a = i.parent.convertToWorldSpaceAR(i.getPosition())
                          , r = o.resultNodeList[n].parent.convertToNodeSpaceAR(a);
                        cc.tween(o.resultNodeList[n]).to(.25, {
                            x: r.x,
                            y: r.y,
                            scale: .9
                        }, {
                            easing: "quadOut"
                        }).call(function() {
                            t.isRepeat(),
                            t.animation.delete(i.uuid)
                        }).start(),
                        e = n
                    }, o = this, i = 0; i < this.resultNodeList.length; i++)
                        n(i),
                        i = e
            }
            ,
            e.prototype.handle_withdraw = function() {
                var t = this;
                if (1 != this.checkPoint)
                    if (null != this.tempAction) {
                        if (!this.is_pause && this.tempAction.node.isValid) {
                            var e = s.default.recall;
                            e <= 0 ? f.default.handleVideo(p.default.VIDEO_TYPE.GET_PROPS).then(function(e) {
                                e && t.withdraw_run()
                            }) : (s.default.recall = e - 1,
                            this.initProp(),
                            this.withdraw_run())
                        }
                    } else
                        c.default.INS.openPanel({
                            layer: c.Layer.gameBoxLayer,
                            panel: _.default,
                            param: {
                                title: "\u5e95\u90e8\u680f\u6846\u6ca1\u6709\u4efb\u4f55\u53ef\u64a4\u9500\u7684\u6c34\u679c",
                                duration: 1e3
                            }
                        })
            }
            ,
            e.prototype.withdraw_run = function() {
                if (d.default.play("undo").then(),
                "" != this.tempAction.node.name) {
                    if (this.onTouch(this.tempAction.node, this.handle_majiang),
                    this.tempAction.maskItem)
                        for (var t = this.tempAction.maskItem, e = 0; e < t.length; e++)
                            t[e].getComponent(h.default).isClick = !1,
                            t[e].getChildByName("mask").active = !0;
                    cc.tween(this.tempAction.node).to(.25, {
                        x: this.tempAction.startPos.x,
                        y: this.tempAction.startPos.y,
                        scale: 1
                    }).start();
                    var n = null == this.tempAction.index ? this.resultData.length - 1 : this.tempAction.index;
                    this.resultData.splice(n, 1),
                    this.resultNodeList.splice(n, 1),
                    this.refreshNode(),
                    this.tempAction = null
                }
            }
            ,
            e.prototype.handle_hint = function() {
                var t = this;
                if (1 != this.checkPoint && !(this.is_pause || this.resultData.length >= 6)) {
                    var e = this.nowTime
                      , n = (new Date).getTime();
                    if (!(e && n - e <= 1500)) {
                        this.nowTime = n;
                        var o = s.default.hint;
                        o <= 0 ? f.default.handleVideo(p.default.VIDEO_TYPE.GET_PROPS).then(function(e) {
                            e && t.hint_run()
                        }) : (s.default.hint = o - 1,
                        this.initProp(),
                        this.hint_run())
                    }
                }
            }
            ,
            e.prototype.autoFlyItem = function(t, e) {
                for (var n = 0, o = [], i = this.gameContent.childrenCount - 1; i >= 0; i--)
                    if (0 != this.gameContent.children[i].childrenCount) {
                        n = i;
                        break
                    }
                for (var a = n; a >= 0; a--) {
                    null == t && a == n && (t = this.gameContent.children[a].children[0].getComponent(h.default).itemData);
                    for (var r = this.gameContent.children[a], c = 0; c < r.childrenCount && o.length != e; c++) {
                        for (var s = !1, l = 0; l < this.resultNodeList.length; l++)
                            this.resultNodeList[l] == r.children[c] && (s = !0);
                        s || r.children[c].getComponent(h.default).itemData == t && o.push(r.children[c])
                    }
                }
                for (i = 0; i < o.length; i++) {
                    for (var u = !1, d = null, f = o[i].getComponent(h.default).itemData, p = 0; p < this.resultData.length; p++)
                        this.resultData[p] == f && (d = p + 1,
                        u = !0);
                    u ? this.resultData.splice(d, 0, f) : this.resultData.push(f),
                    this.flyItem(o[i], f, d),
                    this.maskItem(o[i])
                }
            }
            ,
            e.prototype.hint_run = function() {
                if (d.default.play("hint").then(),
                this.resultData.length) {
                    for (var t = {}, e = null, n = 0; n < this.resultData.length; n++) {
                        0 == n && (e = this.resultData[n]);
                        var o = this.resultData[n];
                        t[o] = t[o] + 1 || 1
                    }
                    var i = 3 - t[e];
                    this.autoFlyItem(e, i)
                } else
                    this.autoFlyItem(null, 3)
            }
            ,
            e.prototype.handle_replay = function() {
                if (!this.is_pause) {
                    this.is_pause = !0,
                    d.default.play("replay").then(),
                    this.tempTime = this.allTime,
                    this.gameContent.removeAllChildren();
                    for (var t = 0; t < this.resultNodeList.length; t++)
                        this.resultNodeList[t].destroy();
                    this.resultData = [],
                    this.resultNodeList = [],
                    this.tempAction = null,
                    this.initGame()
                }
            }
            ,
            e.prototype.hide = function() {}
            ,
            n = a([v], e)
        }(r.default));
        n.default = b,
        cc._RF.pop()
    }
    , {
        "../../Common/Constant": "Constant",
        "../../Common/Tools": "Tools",
        "../../Common/manage/AudioMgr": "AudioMgr",
        "../../Common/manage/CacheMgr": "CacheMgr",
        "../../Common/manage/Layer/LayerPanel": "LayerPanel",
        "../../Common/manage/LoadMgr": "LoadMgr",
        "../../Common/manage/PanelMgr": "PanelMgr",
        "../../Common/manage/Tips/ToastTips": "ToastTips",
        "../Game/Item": "Item",
        "../Game/gameConfig": "gameConfig",
        "./EndView": "EndView",
        "./HomeView": "HomeView"
    }],
    Game: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "8438fx1oexOf4Jrd9uEt5U9", "Game");
        var o, i = this && this.__extends || (o = function(t, e) {
            return (o = Object.setPrototypeOf || {
                __proto__: []
            }instanceof Array && function(t, e) {
                t.__proto__ = e
            }
            || function(t, e) {
                for (var n in e)
                    Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n])
            }
            )(t, e)
        }
        ,
        function(t, e) {
            function n() {
                this.constructor = t
            }
            o(t, e),
            t.prototype = null === e ? Object.create(e) : (n.prototype = e.prototype,
            new n)
        }
        ), a = this && this.__decorate || function(t, e, n, o) {
            var i, a = arguments.length, r = a < 3 ? e : null === o ? o = Object.getOwnPropertyDescriptor(e, n) : o;
            if ("object" == typeof Reflect && "function" == typeof Reflect.decorate)
                r = Reflect.decorate(t, e, n, o);
            else
                for (var c = t.length - 1; c >= 0; c--)
                    (i = t[c]) && (r = (a < 3 ? i(r) : a > 3 ? i(e, n, r) : i(e, n)) || r);
            return a > 3 && r && Object.defineProperty(e, n, r),
            r
        }
        ;
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var r = t("../Common/manage/PanelMgr")
          , c = t("../Common/manage/Emit/Emit")
          , s = t("../Common/manage/Emit/EmitData")
          , l = t("../Moudle/View/HomeView")
          , u = t("../Common/manage/AudioMgr");
        cc.macro.CLEANUP_IMAGE_CACHE = !1,
        cc.dynamicAtlasManager.enabled = !0;
        var d = cc._decorator
          , h = d.ccclass
          , f = (d.property,
        function(t) {
            function e() {
                var e = null !== t && t.apply(this, arguments) || this;
                return e.BannerInit = !1,
                e
            }
            var n;
            return i(e, t),
            n = e,
            e.prototype.onLoad = function() {
                u.default.backMusic(),
                n.Ins = this,
                r.default.INS ? this.do_after_panelMgr_initOK() : c.default.instance().on(s.EventCode.PanelMgrInitOK, this.do_after_panelMgr_initOK, this)
            }
            ,
            e.prototype.do_after_panelMgr_initOK = function() {
                r.default.INS.openPanel({
                    layer: r.Layer.gameLayer,
                    panel: l.default
                })
            }
            ,
            e.Ins = null,
            n = a([h], e)
        }(cc.Component));
        n.default = f,
        cc._RF.pop()
    }
    , {
        "../Common/manage/AudioMgr": "AudioMgr",
        "../Common/manage/Emit/Emit": "Emit",
        "../Common/manage/Emit/EmitData": "EmitData",
        "../Common/manage/PanelMgr": "PanelMgr",
        "../Moudle/View/HomeView": "HomeView"
    }],
    Global: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "09dc9O+/1xHuLxuoXl8x+Mz", "Global"),
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var o = function() {
            function t() {}
            return t.config = {
                gameInfo: {
                    animation: .5,
                    maxStamina: 10,
                    autoAddStaminaTime: 1,
                    autoAddStaminaNum: 1
                },
                addInfo: {
                    gold: 100,
                    diamond: 2,
                    stamina: 2
                }
            },
            t.IS_LOG = !1,
            t.LoginFlag = !1,
            t.LoginType = 3,
            t
        }();
        n.default = o,
        cc._RF.pop()
    }
    , {}],
    HomeView: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "cb3bfFe0WFPIJpKXt1o4F5F", "HomeView");
        var o, i = this && this.__extends || (o = function(t, e) {
            return (o = Object.setPrototypeOf || {
                __proto__: []
            }instanceof Array && function(t, e) {
                t.__proto__ = e
            }
            || function(t, e) {
                for (var n in e)
                    Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n])
            }
            )(t, e)
        }
        ,
        function(t, e) {
            function n() {
                this.constructor = t
            }
            o(t, e),
            t.prototype = null === e ? Object.create(e) : (n.prototype = e.prototype,
            new n)
        }
        ), a = this && this.__decorate || function(t, e, n, o) {
            var i, a = arguments.length, r = a < 3 ? e : null === o ? o = Object.getOwnPropertyDescriptor(e, n) : o;
            if ("object" == typeof Reflect && "function" == typeof Reflect.decorate)
                r = Reflect.decorate(t, e, n, o);
            else
                for (var c = t.length - 1; c >= 0; c--)
                    (i = t[c]) && (r = (a < 3 ? i(r) : a > 3 ? i(e, n, r) : i(e, n)) || r);
            return a > 3 && r && Object.defineProperty(e, n, r),
            r
        }
        ;
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var r = t("../../Common/manage/Layer/LayerPanel")
          , c = t("../../Common/manage/PanelMgr")
          , s = t("./GameView")
          , l = t("./GameInfoView")
          , u = t("../../Common/manage/LoadMgr")
          , d = t("../../Common/Tools")
          , h = cc._decorator.ccclass
          , f = function(t) {
            function e() {
                var e = null !== t && t.apply(this, arguments) || this;
                return e._setting = null,
                e.shopNode = null,
                e.signInNode = null,
                e
            }
            var n;
            return i(e, t),
            n = e,
            e.getUrl = function() {
                return {
                    bundle: "homeView",
                    name: "homeView"
                }
            }
            ,
            e.prototype.initUI = function() {
                var t = this;
                c.default.INS.openPanel({
                    panel: l.default,
                    layer: c.Layer.gameInfoLayer
                }),
                this.onTouch(this.getNode("next"), function() {
                    d.default.changeStamina(-1, function() {
                        c.default.INS.openPanel({
                            layer: c.Layer.gameLayer,
                            panel: s.default,
                            call: function() {
                                c.default.INS.closePanel(n)
                            }
                        })
                    })
                }),
                this._setting = this.getNode("setting"),
                this.onTouch(this._setting, function() {
                    u.default.loadPrefab("game/settingBox").then(function(e) {
                        var n = cc.instantiate(e);
                        t.node.addChild(n)
                    })
                }),
                this.signInNode = this.getNode("signIn"),
                this.onTouch(this.signInNode, function() {
                    u.default.loadPrefab("game/signIn").then(function(e) {
                        var n = cc.instantiate(e);
                        t.node.addChild(n)
                    })
                }),
                this.shopNode = this.getNode("shop"),
                this.onTouch(this.shopNode, function() {
                    u.default.loadPrefab("game/shop").then(function(e) {
                        var n = cc.instantiate(e);
                        t.node.addChild(n)
                    })
                })
            }
            ,
            e.prototype.show = function() {}
            ,
            e.prototype.hide = function() {}
            ,
            n = a([h], e)
        }(r.default);
        n.default = f,
        cc._RF.pop()
    }
    , {
        "../../Common/Tools": "Tools",
        "../../Common/manage/Layer/LayerPanel": "LayerPanel",
        "../../Common/manage/LoadMgr": "LoadMgr",
        "../../Common/manage/PanelMgr": "PanelMgr",
        "./GameInfoView": "GameInfoView",
        "./GameView": "GameView"
    }],
    Item: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "49b82TyMtVJTpzVh/egjltj", "Item");
        var o, i = this && this.__extends || (o = function(t, e) {
            return (o = Object.setPrototypeOf || {
                __proto__: []
            }instanceof Array && function(t, e) {
                t.__proto__ = e
            }
            || function(t, e) {
                for (var n in e)
                    Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n])
            }
            )(t, e)
        }
        ,
        function(t, e) {
            function n() {
                this.constructor = t
            }
            o(t, e),
            t.prototype = null === e ? Object.create(e) : (n.prototype = e.prototype,
            new n)
        }
        ), a = this && this.__decorate || function(t, e, n, o) {
            var i, a = arguments.length, r = a < 3 ? e : null === o ? o = Object.getOwnPropertyDescriptor(e, n) : o;
            if ("object" == typeof Reflect && "function" == typeof Reflect.decorate)
                r = Reflect.decorate(t, e, n, o);
            else
                for (var c = t.length - 1; c >= 0; c--)
                    (i = t[c]) && (r = (a < 3 ? i(r) : a > 3 ? i(e, n, r) : i(e, n)) || r);
            return a > 3 && r && Object.defineProperty(e, n, r),
            r
        }
        ;
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var r = cc._decorator
          , c = r.ccclass
          , s = (r.property,
        function(t) {
            function e() {
                var e = null !== t && t.apply(this, arguments) || this;
                return e.itemData = null,
                e.hierarchy = null,
                e.whereItem = null,
                e.isClick = !1,
                e
            }
            return i(e, t),
            a([c], e)
        }(cc.Component));
        n.default = s,
        cc._RF.pop()
    }
    , {}],
    LayerMgr: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "91564A1TFxPHpZ2/kzicOPw", "LayerMgr"),
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var o = function() {
            function t() {}
            return t.init = function(t) {
                this.gameLayer = t.gameLayer,
                this.boxLayer = t.boxLayer,
                this.bannerLayer = t.bannerLayer,
                this.chestLayer = t.chestLayer,
                this.gameInfoLayer = t.gameInfoLayer,
                this.gameBoxLayer = t.gameBoxLayer,
                this.sliderLayer = t.sliderLayer
            }
            ,
            t.gameLayer = null,
            t.boxLayer = null,
            t.bannerLayer = null,
            t.chestLayer = null,
            t.gameInfoLayer = null,
            t.gameBoxLayer = null,
            t.sliderLayer = null,
            t
        }();
        n.default = o,
        cc._RF.pop()
    }
    , {}],
    LayerPanel: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "ee296Xoo4NLQ6NOmcdiL7Pb", "LayerPanel");
        var o, i = this && this.__extends || (o = function(t, e) {
            return (o = Object.setPrototypeOf || {
                __proto__: []
            }instanceof Array && function(t, e) {
                t.__proto__ = e
            }
            || function(t, e) {
                for (var n in e)
                    Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n])
            }
            )(t, e)
        }
        ,
        function(t, e) {
            function n() {
                this.constructor = t
            }
            o(t, e),
            t.prototype = null === e ? Object.create(e) : (n.prototype = e.prototype,
            new n)
        }
        ), a = this && this.__decorate || function(t, e, n, o) {
            var i, a = arguments.length, r = a < 3 ? e : null === o ? o = Object.getOwnPropertyDescriptor(e, n) : o;
            if ("object" == typeof Reflect && "function" == typeof Reflect.decorate)
                r = Reflect.decorate(t, e, n, o);
            else
                for (var c = t.length - 1; c >= 0; c--)
                    (i = t[c]) && (r = (a < 3 ? i(r) : a > 3 ? i(e, n, r) : i(e, n)) || r);
            return a > 3 && r && Object.defineProperty(e, n, r),
            r
        }
        ;
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var r = t("./LayerUI")
          , c = t("../GameLogMgr")
          , s = cc._decorator.ccclass
          , l = function(t) {
            function e() {
                var e = null !== t && t.apply(this, arguments) || this;
                return e.assets = [],
                e
            }
            return i(e, t),
            e.getUrl = function() {
                return c.default.error("\u9700\u8981\u91cd\u5199getURL"),
                null
            }
            ,
            e.prototype.gameBoxUrl = function() {
                return "GameBox"
            }
            ,
            e.prototype.moreGameUrl = function() {
                return "more_game"
            }
            ,
            e.prototype.onDestroyDo = function() {}
            ,
            a([s], e)
        }(r.default);
        n.default = l,
        cc._RF.pop()
    }
    , {
        "../GameLogMgr": "GameLogMgr",
        "./LayerUI": "LayerUI"
    }],
    LayerUI: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "07b55fvQGxH34PGtUdVwK/o", "LayerUI");
        var o, i = this && this.__extends || (o = function(t, e) {
            return (o = Object.setPrototypeOf || {
                __proto__: []
            }instanceof Array && function(t, e) {
                t.__proto__ = e
            }
            || function(t, e) {
                for (var n in e)
                    Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n])
            }
            )(t, e)
        }
        ,
        function(t, e) {
            function n() {
                this.constructor = t
            }
            o(t, e),
            t.prototype = null === e ? Object.create(e) : (n.prototype = e.prototype,
            new n)
        }
        ), a = this && this.__decorate || function(t, e, n, o) {
            var i, a = arguments.length, r = a < 3 ? e : null === o ? o = Object.getOwnPropertyDescriptor(e, n) : o;
            if ("object" == typeof Reflect && "function" == typeof Reflect.decorate)
                r = Reflect.decorate(t, e, n, o);
            else
                for (var c = t.length - 1; c >= 0; c--)
                    (i = t[c]) && (r = (a < 3 ? i(r) : a > 3 ? i(e, n, r) : i(e, n)) || r);
            return a > 3 && r && Object.defineProperty(e, n, r),
            r
        }
        ;
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var r = t("../AudioMgr")
          , c = t("../GameLogMgr")
          , s = cc._decorator.ccclass
          , l = function(t) {
            function e() {
                var e = null !== t && t.apply(this, arguments) || this;
                return e._touchList = {},
                e._touchEndList = {},
                e._enableList = {},
                e
            }
            return i(e, t),
            e.prototype.setInteractable = function(t, e, n) {
                if (void 0 === n && (n = !0),
                t) {
                    var o = t.getComponent(cc.Button);
                    o && (o.enableAutoGrayEffect = n,
                    o.interactable = e),
                    this._enableList[t.name] = {
                        enabled: e,
                        isGray: n
                    }
                }
            }
            ,
            e.prototype.onTouch = function(t, e, n, o, i) {
                var a = this;
                if (void 0 === n && (n = "click"),
                void 0 === o && (o = .9),
                void 0 === i && (i = !0),
                t && e) {
                    var s = t.name;
                    if (this._touchList[s] && this._touchList[s].target == t)
                        c.default.warn("\u91cd\u590d\u8bbe\u7f6e--\x3e", s);
                    else {
                        var l = t.getComponent(cc.Button);
                        1 != o && (l || ((l = t.addComponent(cc.Button)).transition = cc.Button.Transition.SCALE,
                        l.zoomScale = o));
                        var u = !0
                          , d = !0;
                        this._enableList[t.name] && (u = this._enableList[t.name].enabled,
                        d = this._enableList[t.name].isGray),
                        this.setInteractable(t, u, d);
                        var h = this
                          , f = function(o) {
                            var c = (a._enableList[t.name] || {}).enabled;
                            (void 0 === c || c) && (i && o.stopPropagation(),
                            n && "" != n && r.default.play(n).then(),
                            e.call(h, o))
                        };
                        t.on(cc.Node.EventType.TOUCH_START, f),
                        this._touchList[s] = {
                            target: t,
                            handler: f,
                            callObj: h
                        }
                    }
                } else
                    c.default.error("target || handler\u4e3a\u7a7a--\x3e", t, e)
            }
            ,
            e.prototype.onTouchEnd = function(t, e) {
                if (t && e) {
                    var n = t.name;
                    this._touchEndList[n] && this._touchEndList[n].target == t && c.default.warn("\u91cd\u590d\u8bbe\u7f6e --\x3e onTouchEnd ", n);
                    var o = this
                      , i = function(t) {
                        e.call(o, t)
                    };
                    t.on(cc.Node.EventType.TOUCH_END, i),
                    t.on(cc.Node.EventType.TOUCH_CANCEL, i),
                    this._touchEndList[n] = {
                        target: t,
                        handler: i,
                        callObj: o
                    }
                } else
                    c.default.error("target || handle\u4e3a\u7a7a ondTouchEnd --\x3e", t, e)
            }
            ,
            e.prototype.offTouchEnd = function(t) {
                if (t) {
                    var e = t.name;
                    if (this._touchEndList[e]) {
                        var n = this._touchEndList[e].handler;
                        t.off(cc.Node.EventType.TOUCH_END, n),
                        t.off(cc.Node.EventType.TOUCH_CANCEL, n),
                        delete this._touchEndList[e]
                    }
                } else
                    c.default.error("target \u4e3a\u7a7a ")
            }
            ,
            e.prototype.offTouch = function(t) {
                if (t) {
                    var e = t.name;
                    if (this._touchList[e]) {
                        var n = this._touchList[e].handler;
                        t.off(cc.Node.EventType.TOUCH_START, n),
                        delete this._touchList[e]
                    }
                    delete this._enableList[e]
                } else
                    c.default.error("target \u4e3a\u7a7a")
            }
            ,
            e.prototype.clear = function() {
                for (var t in this._touchList)
                    this.offTouch(this._touchList[t].target)
            }
            ,
            e.prototype.onDestroy = function() {
                this.clear()
            }
            ,
            e.prototype.getNode = function(t) {
                var e = null;
                return "" != t && t ? ((e = -1 != t.indexOf("/") ? cc.find(t, this.node) : this.node.getChildByName(t)) || c.default.warn("\u672a\u627e\u5230\u8be5\u8282\u70b9  path=", t),
                e) : null
            }
            ,
            a([s], e)
        }(cc.Component);
        n.default = l,
        cc._RF.pop()
    }
    , {
        "../AudioMgr": "AudioMgr",
        "../GameLogMgr": "GameLogMgr"
    }],
    LoadMgr: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "7f744UhRIBHxoK05CsVLTZs", "LoadMgr"),
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var o = t("./GameLogMgr")
          , i = cc.Texture2D
          , a = t("./GameLogMgr")
          , r = function() {
            function t() {}
            return t.loadBundle = function(t) {
                var e = this;
                return new Promise(function(n, o) {
                    for (var i = [], a = 0; a < t.length; a++) {
                        var r = t[a];
                        e.alreadyLoadBundle.has(r) || i.push(e.loadBundle_Single(r))
                    }
                    Promise.all(i).then(function(t) {
                        n(t)
                    }, function() {
                        o(!1)
                    })
                }
                )
            }
            ,
            t.loadBundle_Single = function(t) {
                var e = this;
                return new Promise(function(n, o) {
                    cc.assetManager.loadBundle(t, function(i, r) {
                        if (i)
                            return a.default.error("\u52a0\u8f7d\u5206\u5305\u5931\u8d25\uff01\uff01\uff01\uff01\uff01\uff01\uff01\uff01\uff01", i, "name :", t),
                            void o(!1);
                        e.alreadyLoadBundle.set(t, r),
                        n(r)
                    })
                }
                )
            }
            ,
            t.judgeBundleLoad = function(t) {
                return this.alreadyLoadBundle.has(t)
            }
            ,
            t.getBundle = function(t) {
                return this.alreadyLoadBundle.get(t)
            }
            ,
            t.init_bundleMgr = function() {
                this.loadBundle_Single("homeView").then(),
                this.loadBundle_Single("gameView").then()
            }
            ,
            t.loadSprite = function(t, e, n, i) {
                var a = this;
                return void 0 === n && (n = this.getBundle("sub")),
                void 0 === i && (i = !0),
                new Promise(function(r, c) {
                    if (a._sprite.hasOwnProperty(e))
                        return t.spriteFrame = a._sprite[e],
                        i && (t.node.active = !0),
                        void r(a._sprite[e]);
                    n.load("image/" + e, cc.SpriteFrame, function(n, s) {
                        if (n)
                            return o.default.error(e, " \u56fe\u7247\u52a0\u8f7d\u9519\u8bef ", n),
                            void c(!1);
                        a._sprite[e] = s,
                        t.spriteFrame = s,
                        i && (t.node.active = !0),
                        r(s)
                    })
                }
                )
            }
            ,
            t.loadRemoteSprite = function(t, e, n) {
                var a = this;
                return void 0 === e && (e = null),
                void 0 === n && (n = !0),
                new Promise(function(r, c) {
                    a._remote_Sprite.get(t) ? (e && (e.spriteFrame = a._remote_Sprite.get(t)),
                    n && e && (e.node.active = !0),
                    r(a._remote_Sprite.get(t))) : cc.assetManager.loadRemote(t, i, function(i, s) {
                        if (0 == s.width) {
                            var l = cc.assetManager.cacheManager.getTemp(t);
                            cc.assetManager.loadRemote(l, function(e, n) {
                                if (e)
                                    return o.default.warn("\u7b2c\u4e8c\u6b21\u52a0\u8f7d\u8fdc\u7a0b\u56fe\u7247\u5931\u8d25", e),
                                    void c(!1);
                                a._remote_Sprite.set(t, new cc.SpriteFrame(n))
                            }),
                            e && (e.spriteFrame = a._remote_Sprite.get(t)),
                            r(a._remote_Sprite.get(t))
                        } else {
                            if (i)
                                return o.default.warn("\u52a0\u8f7d\u8fdc\u7a0b\u56fe\u7247\u5931\u8d25", i),
                                void c(!1);
                            a._remote_Sprite.set(t, new cc.SpriteFrame(s))
                        }
                        n && e && (e.node.active = !0,
                        e.spriteFrame = a._remote_Sprite.get(t)),
                        r(a._remote_Sprite.get(t))
                    })
                }
                )
            }
            ,
            t.loadPrefab = function(t, e) {
                var n = this;
                return void 0 === e && (e = this.getBundle("sub")),
                new Promise(function(i, a) {
                    n._prefabCaches.hasOwnProperty(t) ? i(n._prefabCaches[t]) : e.load("prefab/" + t, cc.Prefab, function(e, r) {
                        if (e)
                            return o.default.error("setPrefab error", e, t),
                            void a(!1);
                        n._prefabCaches[t] = r,
                        i(r)
                    })
                }
                )
            }
            ,
            t.load_AudioClip = function(t, e) {
                var n = this;
                return void 0 === e && (e = this.getBundle("sub")),
                new Promise(function(i, a) {
                    if (n._audio_caches.get(t)) {
                        var r = n._audio_caches.get(t);
                        i(r)
                    } else
                        e.load("audio/" + t, cc.AudioClip, function(e, r) {
                            if (e)
                                return o.default.error("\u52a0\u8f7d\u97f3\u9891\u5931\u8d25", e, "url : ", t),
                                void a(null);
                            n._audio_caches.set(t, r),
                            i(r)
                        })
                }
                )
            }
            ,
            t.loadAtlas = function(t, e) {
                var n = this;
                return void 0 === e && (e = this.getBundle("sub")),
                new Promise(function(i, a) {
                    n._AtlasCaches.hasOwnProperty(t) ? i(n._AtlasCaches[t]) : e.load("image/" + t, cc.SpriteAtlas, function(e, r) {
                        if (e)
                            return o.default.log("setAtlas error", e, t),
                            void a(!1);
                        n._AtlasCaches[t] = r,
                        i(r)
                    })
                }
                )
            }
            ,
            t._sprite = {},
            t.alreadyLoadBundle = new Map,
            t._remote_Sprite = new Map,
            t._prefabCaches = {},
            t._audio_caches = new Map,
            t._AtlasCaches = {},
            t
        }();
        n.default = r,
        cc._RF.pop()
    }
    , {
        "./GameLogMgr": "GameLogMgr"
    }],
    Loading: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "ec6e2rgMvFB4oPzJeQ8YAZt", "Loading");
        var o, i = this && this.__extends || (o = function(t, e) {
            return (o = Object.setPrototypeOf || {
                __proto__: []
            }instanceof Array && function(t, e) {
                t.__proto__ = e
            }
            || function(t, e) {
                for (var n in e)
                    Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n])
            }
            )(t, e)
        }
        ,
        function(t, e) {
            function n() {
                this.constructor = t
            }
            o(t, e),
            t.prototype = null === e ? Object.create(e) : (n.prototype = e.prototype,
            new n)
        }
        ), a = this && this.__decorate || function(t, e, n, o) {
            var i, a = arguments.length, r = a < 3 ? e : null === o ? o = Object.getOwnPropertyDescriptor(e, n) : o;
            if ("object" == typeof Reflect && "function" == typeof Reflect.decorate)
                r = Reflect.decorate(t, e, n, o);
            else
                for (var c = t.length - 1; c >= 0; c--)
                    (i = t[c]) && (r = (a < 3 ? i(r) : a > 3 ? i(e, n, r) : i(e, n)) || r);
            return a > 3 && r && Object.defineProperty(e, n, r),
            r
        }
        ;
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var r = t("../Common/Tools")
          , c = t("../Common/Test")
          , s = t("../Common/manage/LoadMgr")
          , l = cc._decorator
          , u = l.ccclass
          , d = l.property
          , h = function(t) {
            function e() {
                var e = null !== t && t.apply(this, arguments) || this;
                return e.round = null,
                e.mask = null,
                e.tween = null,
                e
            }
            return i(e, t),
            e.prototype.onLoad = function() {
                var t = this;
                this.mask.width = 0,
                this.tween = cc.tween(this.mask).to(5, {
                    width: 500
                }, {
                    easing: "quadOut"
                }).start();
                var e = 0;
                cc.director.preloadScene("Game"),
                s.default.init_bundleMgr(),
                c.default.start("\u52a0\u8f7d\u603b\u65f6\u957f");
                var n = r.default.model_initModel(function() {
                    ++e === n && (c.default.end("\u52a0\u8f7d\u603b\u65f6\u957f"),
                    t.tween.stop(),
                    cc.tween(t.mask).to(.2, {
                        width: 500
                    }, {
                        easing: "quadOut"
                    }).call(function() {
                        cc.director.loadScene("Game")
                    }).start())
                })
            }
            ,
            a([d(cc.Node)], e.prototype, "round", void 0),
            a([d(cc.Node)], e.prototype, "mask", void 0),
            a([u], e)
        }(cc.Component);
        n.default = h,
        cc._RF.pop()
    }
    , {
        "../Common/Test": "Test",
        "../Common/Tools": "Tools",
        "../Common/manage/LoadMgr": "LoadMgr"
    }],
    PanelMgr: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "a5e36JQ0GRBYYm5P5DBYjVC", "PanelMgr");
        var o, i = this && this.__extends || (o = function(t, e) {
            return (o = Object.setPrototypeOf || {
                __proto__: []
            }instanceof Array && function(t, e) {
                t.__proto__ = e
            }
            || function(t, e) {
                for (var n in e)
                    Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n])
            }
            )(t, e)
        }
        ,
        function(t, e) {
            function n() {
                this.constructor = t
            }
            o(t, e),
            t.prototype = null === e ? Object.create(e) : (n.prototype = e.prototype,
            new n)
        }
        ), a = this && this.__decorate || function(t, e, n, o) {
            var i, a = arguments.length, r = a < 3 ? e : null === o ? o = Object.getOwnPropertyDescriptor(e, n) : o;
            if ("object" == typeof Reflect && "function" == typeof Reflect.decorate)
                r = Reflect.decorate(t, e, n, o);
            else
                for (var c = t.length - 1; c >= 0; c--)
                    (i = t[c]) && (r = (a < 3 ? i(r) : a > 3 ? i(e, n, r) : i(e, n)) || r);
            return a > 3 && r && Object.defineProperty(e, n, r),
            r
        }
        ;
        Object.defineProperty(n, "__esModule", {
            value: !0
        }),
        n.Box = n.View = n.Layer = void 0;
        var r = t("./GameLogMgr")
          , c = t("./Emit/Emit")
          , s = t("./Emit/EmitData")
          , l = t("./Layer/LayerPanel")
          , u = t("./LoadMgr")
          , d = t("../Global")
          , h = cc._decorator
          , f = h.ccclass
          , p = h.property
          , m = function(t) {
            function e() {
                var e = null !== t && t.apply(this, arguments) || this;
                return e.layers = [],
                e.LoadingList = new Map,
                e.openList = new Map,
                e.hideList = new Map,
                e
            }
            var n;
            return i(e, t),
            n = e,
            e.prototype.onLoad = function() {
                n.INS = this,
                c.default.instance().emit(s.EventCode.PanelMgrInitOK)
            }
            ,
            e.prototype.openPanel = function(t) {
                var e = this
                  , n = this.layers[t.layer];
                if (n) {
                    var o = t.panel.getUrl();
                    if ("homeView" == o.name && d.default.LoginFlag && (d.default.LoginFlag = !1),
                    this.LoadingList.has(o.name))
                        r.default.warn("\u9762\u677f", o.name, "\u5df2\u7ecf\u5728\u52a0\u8f7d\u4e2d\uff0c\u91cd\u590d\u52a0\u8f7d\u5931\u8d25");
                    else if (this.openList.has(t.panel.getUrl().name))
                        r.default.warn("\u4e0d\u5141\u8bb8\u91cd\u590d\u6253\u5f00", t.panel);
                    else {
                        var i;
                        this.LoadingList.set(o.name, 1),
                        i = function() {
                            var i = null;
                            e.hideList.has(o.name) ? ((i = e.hideList.get(o.name)).parent = n,
                            i.active = !1,
                            e.openList.set(o.name, i),
                            e.showPanel(i, t.param),
                            e.LoadingList.delete(o.name),
                            e.LoadingList.size,
                            t.call && t.call()) : u.default.loadPrefab(o.name, u.default.getBundle(o.bundle)).then(function(a) {
                                (i = cc.instantiate(a)).parent = n,
                                i.active = !1,
                                e.openList.set(o.name, i),
                                i.getComponent(l.default).initUI(),
                                e.showPanel(i, t.param),
                                e.LoadingList.delete(o.name),
                                e.LoadingList.size,
                                t.call && t.call()
                            })
                        }
                        ,
                        u.default.judgeBundleLoad(o.name) ? (r.default.log("bundle\u5df2\u7ecf\u52a0\u8f7d\u597d\u4e86:", o.name),
                        i()) : (r.default.log("bundle\u8fd8\u6ca1\u52a0\u8f7d\u597d,\u9700\u8981\u52a0\u8f7d\u4e00\u4e0b"),
                        u.default.loadBundle_Single(o.bundle).then(function() {
                            i()
                        }))
                    }
                } else
                    r.default.error("openPanel layer \u4e3a\u7a7a ,\u6253\u5f00\u5931\u8d25. ", n)
            }
            ,
            e.prototype.showPanel = function(t, e) {
                t.getComponent(l.default).show(e),
                t.active = !0
            }
            ,
            e.prototype.closePanel = function(t, e) {
                void 0 === e && (e = !0);
                var n = this.openList.get(t.getUrl().name);
                n ? (n.getComponent(l.default).hide(),
                n.getComponent(l.default).unscheduleAllCallbacks(),
                n.parent = null,
                this.openList.delete(t.getUrl().name),
                e ? (n.getComponent(l.default).onDestroyDo(),
                n.destroy()) : this.hideList.set(t.getUrl().name, n)) : r.default.warn("close Panel ", t.getUrl(), " error  : \u8be5\u9762\u677f\u5c1a\u672a\u6253\u5f00!")
            }
            ,
            e.prototype.getPanel = function(t) {
                return this.openList.get(t.getUrl().name)
            }
            ,
            a([p({
                type: [cc.Node],
                tooltip: "\u53ea\u8981\u5c06Game\u4e2d\u7684\u573a\u666flayer\u6309\u7167\u987a\u5e8f\u8d4b\u503c\u5373\u53ef\uff0c \u5982\u679c\u5b58\u5728\u4fee\u6539\uff0c\u9700\u8981\u5230PannerMgr.ts\u4e2d\u4fee\u6539\u679a\u4e3e\u53d8\u91cf Layer,\u4e5f\u662f\u9700\u8981\u6309\u7167\u7ed1\u5b9a\u987a\u5e8f"
            })], e.prototype, "layers", void 0),
            n = a([f], e)
        }(cc.Component);
        n.default = m,
        function(t) {
            t[t.gameLayer = 0] = "gameLayer",
            t[t.gameInfoLayer = 1] = "gameInfoLayer",
            t[t.sliderLayer = 2] = "sliderLayer",
            t[t.chestLayer = 3] = "chestLayer",
            t[t.gameBoxLayer = 4] = "gameBoxLayer",
            t[t.bannerLayer = 5] = "bannerLayer"
        }(n.Layer || (n.Layer = {})),
        function(t) {
            t[t.endView = 0] = "endView",
            t[t.gameView = 1] = "gameView",
            t[t.homeView = 2] = "homeView"
        }(n.View || (n.View = {})),
        function(t) {
            t[t.fourBox = 10] = "fourBox",
            t[t.oneBox = 11] = "oneBox"
        }(n.Box || (n.Box = {})),
        cc._RF.pop()
    }
    , {
        "../Global": "Global",
        "./Emit/Emit": "Emit",
        "./Emit/EmitData": "EmitData",
        "./GameLogMgr": "GameLogMgr",
        "./Layer/LayerPanel": "LayerPanel",
        "./LoadMgr": "LoadMgr"
    }],
    PondMgr: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "59aae5+cptD1bbCVBOsqtwq", "PondMgr"),
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var o = t("./GameLogMgr")
          , i = t("./LoadMgr")
          , a = function() {
            function t() {}
            return t.addToCaches = function(t, e, n) {
                if (void 0 === n && (n = 1),
                t && e)
                    return !this.caches[t] && (this.caches[t] = e,
                    this.createToPool(t, n),
                    !0)
            }
            ,
            t.createToPool = function(t, e) {
                if (void 0 === e && (e = 1),
                t && this.caches[t]) {
                    null == this.gamePool[t] && (this.gamePool[t] = new cc.NodePool),
                    e -= this.gamePool[t].size();
                    for (var n = 0; n < e; n++) {
                        var o = cc.instantiate(this.caches[t]);
                        this.gamePool[t].put(o)
                    }
                }
            }
            ,
            t.putNodeToPool = function(t, e) {
                null != e && "" != t && null != t ? (null == this.gamePool[t] && (this.gamePool[t] = new cc.NodePool),
                e.parent = null,
                this.gamePool[t].put(e)) : o.default.warn("putNodeToPool fail", t, e)
            }
            ,
            t.getNodeFromPool = function(t) {
                var e = null;
                return null == this.gamePool[t] && (this.gamePool[t] = new cc.NodePool),
                this.gamePool[t].size() > 0 ? e = this.gamePool[t].get() : this.caches[t] && (e = cc.instantiate(this.caches[t])),
                e
            }
            ,
            t.getAsyncNodeToPool = function(t, e) {
                var n = this;
                if (t) {
                    var a = this.getNodeFromPool(t);
                    a ? e && e(a) : i.default.loadPrefab(t).then(function(o) {
                        n.addToCaches(t, o),
                        a = n.getNodeFromPool(t),
                        e && e(a)
                    })
                } else
                    o.default.warn("getAsyncNodeToPool", "url\u4e3a\u7a7a")
            }
            ,
            t.caches = {},
            t.gamePool = {},
            t
        }();
        n.default = a,
        cc._RF.pop()
    }
    , {
        "./GameLogMgr": "GameLogMgr",
        "./LoadMgr": "LoadMgr"
    }],
    SettingBox: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "1b79ep7EutD1ql8pKVwNMyn", "SettingBox");
        var o, i = this && this.__extends || (o = function(t, e) {
            return (o = Object.setPrototypeOf || {
                __proto__: []
            }instanceof Array && function(t, e) {
                t.__proto__ = e
            }
            || function(t, e) {
                for (var n in e)
                    Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n])
            }
            )(t, e)
        }
        ,
        function(t, e) {
            function n() {
                this.constructor = t
            }
            o(t, e),
            t.prototype = null === e ? Object.create(e) : (n.prototype = e.prototype,
            new n)
        }
        ), a = this && this.__decorate || function(t, e, n, o) {
            var i, a = arguments.length, r = a < 3 ? e : null === o ? o = Object.getOwnPropertyDescriptor(e, n) : o;
            if ("object" == typeof Reflect && "function" == typeof Reflect.decorate)
                r = Reflect.decorate(t, e, n, o);
            else
                for (var c = t.length - 1; c >= 0; c--)
                    (i = t[c]) && (r = (a < 3 ? i(r) : a > 3 ? i(e, n, r) : i(e, n)) || r);
            return a > 3 && r && Object.defineProperty(e, n, r),
            r
        }
        ;
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var r = t("../../Common/manage/CacheMgr")
          , c = t("../../Common/manage/AudioMgr")
          , s = t("../../Common/manage/Layer/LayerUI")
          , l = cc._decorator.ccclass
          , u = function(t) {
            function e() {
                var e = null !== t && t.apply(this, arguments) || this;
                return e.music = null,
                e.audio = null,
                e.data = null,
                e
            }
            return i(e, t),
            e.prototype.onLoad = function() {
                this.music = this.getNode("container/music"),
                this.audio = this.getNode("container/audio"),
                this.data = r.default.setting.setting,
                this.startAnimation()
            }
            ,
            e.prototype.startAnimation = function() {
                this.node.scale = 0,
                cc.tween(this.node).to(.5, {
                    scale: 1
                }, {
                    easing: "backInOut"
                }).start()
            }
            ,
            e.prototype.start = function() {
                var t = this;
                this.getNode("container").children.forEach(function(e) {
                    var n = e.name
                      , o = t.data[n];
                    e.getChildByName("off").active = 0 === o,
                    e.getChildByName("no").active = 0 !== o,
                    t.onTouch(e, function() {
                        e.getChildByName("no").active ? (e.getChildByName("off").active = !0,
                        e.getChildByName("no").active = !1,
                        t.data[n] = 0,
                        "music" == n && c.default.backMusic(!1)) : (e.getChildByName("off").active = !1,
                        e.getChildByName("no").active = !0,
                        t.data[n] = 1,
                        "music" == n && c.default.backMusic()),
                        r.default.setting.setting = t.data,
                        r.default.setting = r.default.setting
                    })
                }),
                this.onTouch(this.getNode("close"), function() {
                    cc.tween(t.node).to(.5, {
                        scale: 0
                    }, {
                        easing: "backInOut"
                    }).call(function() {
                        t.node.destroy()
                    }).start()
                })
            }
            ,
            a([l], e)
        }(s.default);
        n.default = u,
        cc._RF.pop()
    }
    , {
        "../../Common/manage/AudioMgr": "AudioMgr",
        "../../Common/manage/CacheMgr": "CacheMgr",
        "../../Common/manage/Layer/LayerUI": "LayerUI"
    }],
    Shop: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "922degtg3lLnbvyz8L4lOj5", "Shop");
        var o, i = this && this.__extends || (o = function(t, e) {
            return (o = Object.setPrototypeOf || {
                __proto__: []
            }instanceof Array && function(t, e) {
                t.__proto__ = e
            }
            || function(t, e) {
                for (var n in e)
                    Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n])
            }
            )(t, e)
        }
        ,
        function(t, e) {
            function n() {
                this.constructor = t
            }
            o(t, e),
            t.prototype = null === e ? Object.create(e) : (n.prototype = e.prototype,
            new n)
        }
        ), a = this && this.__decorate || function(t, e, n, o) {
            var i, a = arguments.length, r = a < 3 ? e : null === o ? o = Object.getOwnPropertyDescriptor(e, n) : o;
            if ("object" == typeof Reflect && "function" == typeof Reflect.decorate)
                r = Reflect.decorate(t, e, n, o);
            else
                for (var c = t.length - 1; c >= 0; c--)
                    (i = t[c]) && (r = (a < 3 ? i(r) : a > 3 ? i(e, n, r) : i(e, n)) || r);
            return a > 3 && r && Object.defineProperty(e, n, r),
            r
        }
        ;
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var r = t("../../Common/manage/Layer/LayerUI")
          , c = t("./gameConfig")
          , s = t("../../Common/Tools")
          , l = t("../../Common/manage/CacheMgr")
          , u = cc._decorator
          , d = u.ccclass
          , h = (u.property,
        function(t) {
            function e() {
                var e = null !== t && t.apply(this, arguments) || this;
                return e.contentNode = null,
                e.closeNode = null,
                e
            }
            return i(e, t),
            e.prototype.onLoad = function() {
                this.contentNode = this.getNode("ScrollView/view/content"),
                this.closeNode = this.getNode("close")
            }
            ,
            e.prototype.start = function() {
                this.initShop(),
                this.startAnimation(),
                this.onTouch(this.closeNode, this.closeShop)
            }
            ,
            e.prototype.initShop = function() {
                var t = this;
                this.contentNode.children.forEach(function(e, n) {
                    var o = e.getChildByName("btn_buy");
                    o.getChildByName("count").getComponent(cc.Label).string = c.default.shop_price[n] + "",
                    t.onTouch(o, function() {
                        t.handler_buy(n)
                    })
                })
            }
            ,
            e.prototype.handler_buy = function(t) {
                var e = c.default.shop_price[t]
                  , n = c.default.shop_propCount[t];
                if (s.default.changeGold(-e))
                    switch (n.title) {
                    case "recall":
                        var o = l.default.recall
                          , i = n.count;
                        l.default.recall = o + i;
                        break;
                    case "hint":
                        var a = l.default.hint
                          , r = n.count;
                        l.default.hint = a + r;
                        break;
                    default:
                        return
                    }
            }
            ,
            e.prototype.startAnimation = function() {
                this.node.scale = 0,
                cc.tween(this.node).to(.5, {
                    scale: 1
                }, {
                    easing: "backInOut"
                }).start()
            }
            ,
            e.prototype.closeShop = function() {
                var t = this;
                cc.tween(this.node).to(.5, {
                    scale: 0
                }, {
                    easing: "backInOut"
                }).call(function() {
                    t.node.destroy()
                }).start()
            }
            ,
            a([d], e)
        }(r.default));
        n.default = h,
        cc._RF.pop()
    }
    , {
        "../../Common/Tools": "Tools",
        "../../Common/manage/CacheMgr": "CacheMgr",
        "../../Common/manage/Layer/LayerUI": "LayerUI",
        "./gameConfig": "gameConfig"
    }],
    ShortageView: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "9bbd6c1Jv5Ik5gzQRE3I2bs", "ShortageView");
        var o, i = this && this.__extends || (o = function(t, e) {
            return (o = Object.setPrototypeOf || {
                __proto__: []
            }instanceof Array && function(t, e) {
                t.__proto__ = e
            }
            || function(t, e) {
                for (var n in e)
                    Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n])
            }
            )(t, e)
        }
        ,
        function(t, e) {
            function n() {
                this.constructor = t
            }
            o(t, e),
            t.prototype = null === e ? Object.create(e) : (n.prototype = e.prototype,
            new n)
        }
        ), a = this && this.__decorate || function(t, e, n, o) {
            var i, a = arguments.length, r = a < 3 ? e : null === o ? o = Object.getOwnPropertyDescriptor(e, n) : o;
            if ("object" == typeof Reflect && "function" == typeof Reflect.decorate)
                r = Reflect.decorate(t, e, n, o);
            else
                for (var c = t.length - 1; c >= 0; c--)
                    (i = t[c]) && (r = (a < 3 ? i(r) : a > 3 ? i(e, n, r) : i(e, n)) || r);
            return a > 3 && r && Object.defineProperty(e, n, r),
            r
        }
        ;
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var r = t("../../Common/manage/Layer/LayerPanel")
          , c = t("../../Common/Global")
          , s = t("../../Common/manage/LoadMgr")
          , l = t("../../Common/Constant")
          , u = t("../../Common/manage/GameLogMgr")
          , d = t("../../Common/manage/CacheMgr")
          , h = t("../../Common/Tools")
          , f = t("../../Common/manage/PanelMgr")
          , p = cc._decorator
          , m = p.ccclass
          , g = (p.property,
        function(t) {
            function e() {
                var e = null !== t && t.apply(this, arguments) || this;
                return e._image = null,
                e._button = null,
                e._skipButton = null,
                e._addNum = null,
                e.callBack = null,
                e.price = 0,
                e
            }
            var n;
            return i(e, t),
            n = e,
            e.getUrl = function() {
                return {
                    bundle: "shortageView",
                    name: "shortageView"
                }
            }
            ,
            e.prototype.initUI = function() {
                this._image = this.getNode("image"),
                this._button = this.getNode("button"),
                this._skipButton = this.getNode("skip"),
                this._addNum = this.getNode("add_num")
            }
            ,
            e.prototype.show = function(t) {
                var e = this;
                try {
                    var o = "";
                    t && (this.callBack = t.callBack,
                    this.price = t.price,
                    o = t.type),
                    this._image.active = !1,
                    this._skipButton.active = !1,
                    this._button.active = !1,
                    this.replaceSprite(this._image, o),
                    this.replaceSprite(this._skipButton, o),
                    this.replaceSprite(this._button, o);
                    var i = c.default.config.addInfo[o];
                    this._addNum.getComponent(cc.Label).string = "+" + i,
                    this.onTouch(this._button, function() {
                        h.default.handleVideo(l.default.VIDEO_TYPE.GET_GOLD).then(function(t) {
                            t && ("gold" == o ? (d.default.gold = d.default.gold + i,
                            f.default.INS.closePanel(n),
                            e.callBack && d.default.gold >= e.price && (d.default.gold = d.default.gold - e.price,
                            e.callBack())) : "diamond" == o ? (d.default.diamond = d.default.diamond + i,
                            f.default.INS.closePanel(n),
                            e.callBack && d.default.diamond >= e.price && (d.default.diamond = d.default.diamond - e.price,
                            e.callBack())) : "stamina" == o && (d.default.stamina = d.default.stamina + i,
                            f.default.INS.closePanel(n),
                            e.callBack && d.default.stamina >= e.price && (d.default.stamina = d.default.stamina - e.price,
                            e.callBack())))
                        })
                    }),
                    this.onTouch(this._skipButton, function() {
                        f.default.INS.closePanel(n)
                    }),
                    this.node.active = !0
                } catch (a) {
                    u.default.error("home show error ")
                }
            }
            ,
            e.prototype.replaceSprite = function(t, e) {
                if (t && e) {
                    var n = t.getComponent(cc.Sprite);
                    n && s.default.loadSprite(n, "view/shortage/" + e + "/" + t.name).then()
                }
            }
            ,
            e.prototype.hide = function() {}
            ,
            n = a([m], e)
        }(r.default));
        n.default = g,
        cc._RF.pop()
    }
    , {
        "../../Common/Constant": "Constant",
        "../../Common/Global": "Global",
        "../../Common/Tools": "Tools",
        "../../Common/manage/CacheMgr": "CacheMgr",
        "../../Common/manage/GameLogMgr": "GameLogMgr",
        "../../Common/manage/Layer/LayerPanel": "LayerPanel",
        "../../Common/manage/LoadMgr": "LoadMgr",
        "../../Common/manage/PanelMgr": "PanelMgr"
    }],
    SignIn: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "b1272dawuNFC6sl1daczHA8", "SignIn");
        var o, i = this && this.__extends || (o = function(t, e) {
            return (o = Object.setPrototypeOf || {
                __proto__: []
            }instanceof Array && function(t, e) {
                t.__proto__ = e
            }
            || function(t, e) {
                for (var n in e)
                    Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n])
            }
            )(t, e)
        }
        ,
        function(t, e) {
            function n() {
                this.constructor = t
            }
            o(t, e),
            t.prototype = null === e ? Object.create(e) : (n.prototype = e.prototype,
            new n)
        }
        ), a = this && this.__decorate || function(t, e, n, o) {
            var i, a = arguments.length, r = a < 3 ? e : null === o ? o = Object.getOwnPropertyDescriptor(e, n) : o;
            if ("object" == typeof Reflect && "function" == typeof Reflect.decorate)
                r = Reflect.decorate(t, e, n, o);
            else
                for (var c = t.length - 1; c >= 0; c--)
                    (i = t[c]) && (r = (a < 3 ? i(r) : a > 3 ? i(e, n, r) : i(e, n)) || r);
            return a > 3 && r && Object.defineProperty(e, n, r),
            r
        }
        ;
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var r = t("../../Common/manage/Layer/LayerUI")
          , c = t("../../Common/manage/CacheMgr")
          , s = t("./gameConfig")
          , l = t("../../Common/Tools")
          , u = t("../../Common/Constant")
          , d = cc._decorator
          , h = d.ccclass
          , f = (d.property,
        function(t) {
            function e() {
                var e = null !== t && t.apply(this, arguments) || this;
                return e.closeNode = null,
                e.layoutNode = null,
                e.getAwardNode = null,
                e.doubleNode = null,
                e.bodyNode = null,
                e
            }
            return i(e, t),
            e.prototype.onLoad = function() {
                this.closeNode = this.getNode("body/close"),
                this.layoutNode = this.getNode("body/layout"),
                this.getAwardNode = this.getNode("body/get"),
                this.doubleNode = this.getNode("body/double"),
                this.bodyNode = this.getNode("body"),
                this.getAwardNode.active = !1,
                this.doubleNode.active = !1
            }
            ,
            e.prototype.start = function() {
                this.initUI(),
                this.startAnimation(),
                this.onTouch(this.closeNode, this.closeSignIn),
                this.onTouch(this.getAwardNode, this.handler_get),
                this.onTouch(this.doubleNode, this.handler_double)
            }
            ,
            e.prototype.initUI = function() {
                var t = c.default.signInCount
                  , e = c.default.currTimestamp
                  , n = new Date((new Date).toLocaleDateString()).getTime();
                this.layoutNode.children.forEach(function(o, i) {
                    i <= s.default.signInData.length - 1 && (o.getChildByName("count").getComponent(cc.Label).string = s.default.signInData[i] + "");
                    var a = o.getChildByName("can");
                    a.active = !1,
                    o.getChildByName("over").active = !(t <= i),
                    e == n ? a.active = !1 : t == i && (a.active = !0)
                }),
                0 == t ? (this.getAwardNode.active = !0,
                this.doubleNode.active = !0) : n == e ? (this.getAwardNode.active = !1,
                this.doubleNode.active = !1) : (this.getAwardNode.active = !0,
                this.doubleNode.active = !0),
                t >= 7 && (this.getAwardNode.active = !1,
                this.doubleNode.active = !1)
            }
            ,
            e.prototype.handler_get = function() {
                this.getHandler(!1)
            }
            ,
            e.prototype.handler_double = function() {
                var t = this;
                l.default.handleVideo(u.default.VIDEO_TYPE.GET_DOUBLE).then(function(e) {
                    e && t.getHandler(!0)
                })
            }
            ,
            e.prototype.getHandler = function(t) {
                var e = c.default.signInCount;
                if (e <= s.default.signInData.length - 1) {
                    var n = s.default.signInData[e];
                    t ? l.default.changeGold(2 * n) : l.default.changeGold(n)
                } else {
                    var o, i = s.default.prize;
                    o = t ? 2 * i.count : i.count,
                    "hint" == i.type && (c.default.hint = c.default.hint + o)
                }
                c.default.signInCount = e + 1,
                c.default.currTimestamp = new Date((new Date).toLocaleDateString()).getTime(),
                this.initUI()
            }
            ,
            e.prototype.startAnimation = function() {
                this.bodyNode.scale = 0,
                cc.tween(this.bodyNode).to(.5, {
                    scale: 1
                }, {
                    easing: "backInOut"
                }).start()
            }
            ,
            e.prototype.closeSignIn = function() {
                var t = this;
                cc.tween(this.bodyNode).to(.5, {
                    scale: 0
                }, {
                    easing: "backInOut"
                }).call(function() {
                    t.node.destroy()
                }).start()
            }
            ,
            a([h], e)
        }(r.default));
        n.default = f,
        cc._RF.pop()
    }
    , {
        "../../Common/Constant": "Constant",
        "../../Common/Tools": "Tools",
        "../../Common/manage/CacheMgr": "CacheMgr",
        "../../Common/manage/Layer/LayerUI": "LayerUI",
        "./gameConfig": "gameConfig"
    }],
    StorageMgr: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "4b7f5MJKTVDJ7rNpVsnOqxM", "StorageMgr"),
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var o = t("./GameLogMgr")
          , i = function() {
            function t() {}
            return t.read = function(t) {
                if (null != t) {
                    var e = cc.sys.localStorage.getItem(t);
                    return e && (e = JSON.parse(e)),
                    o.default.log("storage read", t, e),
                    e
                }
            }
            ,
            t.save = function(t, e) {
                try {
                    if (o.default.log("storage save", t, e),
                    null != t)
                        return cc.sys.localStorage.setItem(t, JSON.stringify(e))
                } catch (n) {
                    o.default.error(n)
                }
            }
            ,
            t.clear = function() {
                return cc.sys.localStorage.clear()
            }
            ,
            t.rm = function(t) {
                if (null != t)
                    return cc.sys.localStorage.removeItem(t)
            }
            ,
            t
        }();
        n.default = i,
        cc._RF.pop()
    }
    , {
        "./GameLogMgr": "GameLogMgr"
    }],
    Test: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "b5633cJ6yBFCb2RIub9WDst", "Test"),
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var o = t("./manage/GameLogMgr")
          , i = function() {
            function t() {}
            return t.start = function(t) {
                this.timeData.has(t) ? o.default.warn("TestMgr \u91cd\u590d flag ", t) : this.timeData.set(t, (new Date).getTime())
            }
            ,
            t.end = function(t) {
                this.timeData.has(t) ? (o.default.log(t, (new Date).getTime() - this.timeData.get(t)),
                this.timeData.delete(t)) : o.default.warn("flag\u4e0d\u5b58\u5728\uff0c \u65e0\u6cd5\u8ba1\u7b97\u65f6\u5dee", t)
            }
            ,
            t.timeData = new Map,
            t
        }();
        n.default = i,
        cc._RF.pop()
    }
    , {
        "./manage/GameLogMgr": "GameLogMgr"
    }],
    TimerMgr: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "e49b5BloYREHZGgmecvbJv1", "TimerMgr"),
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var o = t("./CacheMgr")
          , i = t("../Tools")
          , a = t("../Global")
          , r = t("../../Moudle/View/GameInfoView")
          , c = function() {
            function t() {
                var t = this;
                window.setInterval(function() {
                    t.update()
                }, 1e3)
            }
            return t.prototype.update = function() {
                var t = new Date;
                if (o.default.stamina >= a.default.config.gameInfo.maxStamina)
                    o.default.lastTimeLogin = 0;
                else if (0 != o.default.lastTimeLogin) {
                    var e = i.default.date_getTimeDifference(t.getTime(), Number(o.default.lastTimeLogin), 2)
                      , n = Math.floor(e.distance / a.default.config.gameInfo.autoAddStaminaTime)
                      , c = e.distance_real % (6e4 * a.default.config.gameInfo.autoAddStaminaTime);
                    n >= 1 && (o.default.stamina >= a.default.config.gameInfo.maxAutoAddStamina || (o.default.stamina + n * a.default.config.gameInfo.autoAddStaminaNum > a.default.config.maxAutoAddStamina ? o.default.stamina = a.default.config.maxAutoAddStamina : o.default.stamina = o.default.stamina + n * a.default.config.gameInfo.autoAddStaminaNum),
                    o.default.lastTimeLogin = t.getTime() - c);
                    var s = r.default.INS();
                    if (s) {
                        var l = 6e4 * a.default.config.gameInfo.autoAddStaminaTime - e.distance_real;
                        s.changeResidue(Math.floor(l / 6e4), Math.floor(l % 6e4 / 1e3))
                    }
                } else
                    o.default.lastTimeLogin = t.getTime()
            }
            ,
            t
        }();
        n.default = new c,
        cc._RF.pop()
    }
    , {
        "../../Moudle/View/GameInfoView": "GameInfoView",
        "../Global": "Global",
        "../Tools": "Tools",
        "./CacheMgr": "CacheMgr"
    }],
    ToastTips: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "87e9dDveelAf4iM6HZE6LGt", "ToastTips");
        var o, i = this && this.__extends || (o = function(t, e) {
            return (o = Object.setPrototypeOf || {
                __proto__: []
            }instanceof Array && function(t, e) {
                t.__proto__ = e
            }
            || function(t, e) {
                for (var n in e)
                    Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n])
            }
            )(t, e)
        }
        ,
        function(t, e) {
            function n() {
                this.constructor = t
            }
            o(t, e),
            t.prototype = null === e ? Object.create(e) : (n.prototype = e.prototype,
            new n)
        }
        ), a = this && this.__decorate || function(t, e, n, o) {
            var i, a = arguments.length, r = a < 3 ? e : null === o ? o = Object.getOwnPropertyDescriptor(e, n) : o;
            if ("object" == typeof Reflect && "function" == typeof Reflect.decorate)
                r = Reflect.decorate(t, e, n, o);
            else
                for (var c = t.length - 1; c >= 0; c--)
                    (i = t[c]) && (r = (a < 3 ? i(r) : a > 3 ? i(e, n, r) : i(e, n)) || r);
            return a > 3 && r && Object.defineProperty(e, n, r),
            r
        }
        ;
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var r = t("../Layer/LayerPanel")
          , c = t("../GameLogMgr")
          , s = t("../PanelMgr")
          , l = cc._decorator.ccclass
          , u = function(t) {
            function e() {
                var e = null !== t && t.apply(this, arguments) || this;
                return e._labelContent = null,
                e
            }
            var n;
            return i(e, t),
            n = e,
            e.getUrl = function() {
                return {
                    bundle: "toastView",
                    name: "toastView"
                }
            }
            ,
            e.prototype.initUI = function() {
                this._labelContent = this.getNode("container/label").getComponent(cc.Label)
            }
            ,
            e.prototype.show = function(t) {
                try {
                    t.title && (this._labelContent.string = t.title),
                    this.node.active = !0;
                    var e = t;
                    t.duration && t.duration > 100 && (e = t.duration / 1e3),
                    c.default.log("end show toast", e),
                    this.scheduleOnce(function() {
                        s.default.INS.closePanel(n, !1)
                    }, e)
                } catch (o) {
                    c.default.error("\u663e\u793a\u5f02\u5e38", o)
                }
            }
            ,
            e.prototype.hide = function() {}
            ,
            n = a([l], e)
        }(r.default);
        n.default = u,
        cc._RF.pop()
    }
    , {
        "../GameLogMgr": "GameLogMgr",
        "../Layer/LayerPanel": "LayerPanel",
        "../PanelMgr": "PanelMgr"
    }],
    Tools: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "b484dylvbFEM6rEcIH7Ekzp", "Tools");
        var o = this && this.__spreadArrays || function() {
            for (var t = 0, e = 0, n = arguments.length; e < n; e++)
                t += arguments[e].length;
            var o = Array(t)
              , i = 0;
            for (e = 0; e < n; e++)
                for (var a = arguments[e], r = 0, c = a.length; r < c; r++,
                i++)
                    o[i] = a[r];
            return o
        }
        ;
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var i = t("./manage/LoadMgr")
          , a = t("./manage/CacheMgr")
          , r = t("./manage/GameLogMgr")
          , c = t("./manage/PanelMgr")
          , s = t("../Moudle/View/ShortageView")
          , l = cc.js.isNumber
          , u = function() {
            function t() {}
            return t.subStr = function(t, e) {
                var n = /[^\x00-\xff]/g;
                if (t.replace(n, "mm").length <= e)
                    return t;
                for (var o = Math.floor(e / 2); o < t.length; o++)
                    if (t.substr(0, o).replace(n, "mm").length >= e)
                        return t.substr(0, o) + "...";
                return t
            }
            ,
            t.deepClone = function(t) {
                try {
                    return JSON.parse(JSON.stringify(t))
                } catch (e) {
                    return t
                }
            }
            ,
            t.getRandomMax = function(t) {
                return Math.floor(Math.random() * t)
            }
            ,
            t.getRandomByArray = function(t) {
                try {
                    return t[this.getRandomMax(t.length)]
                } catch (e) {
                    r.default.error("\u83b7\u53d6\u6570\u7ec4\u968f\u673a\u503c\u5f02\u5e38", e)
                }
                return {}
            }
            ,
            t.getRandom = function(t, e) {
                return Math.floor(Math.random() * (e - t) + t)
            }
            ,
            t.getRealRandom = function(t, e) {
                return Math.random() * (e - t) + t
            }
            ,
            t.sort = function(e, n, o) {
                if (void 0 === n && (n = 0),
                void 0 === o && (o = e.length),
                o <= n)
                    return e;
                for (var i = n, a = o, r = e[n].sort; ; ) {
                    for (; i != a; ) {
                        if (e[a].sort < r) {
                            var c = e[a];
                            e[a] = e[i],
                            e[i] = c;
                            break
                        }
                        a--
                    }
                    for (; i != a; ) {
                        if (e[i].sort > r) {
                            c = e[i],
                            e[i] = e[a],
                            e[a] = c;
                            break
                        }
                        i++
                    }
                    if (i == a)
                        break
                }
                return o - a > 1 && (e = t.sort(e, a + 1, o)),
                i - n > 1 && (e = t.sort(e, n, i)),
                e
            }
            ,
            t.quickExportSort = function(e) {
                if (e.length < 2)
                    return e;
                for (var n = Math.floor(Math.random() * e.length), i = [], a = [], r = 0; r < e.length; r++)
                    e[r].sort >= e[n].sort && r !== n && a.push(e[r]),
                    e[r].sort < e[n].sort && r !== n && i.push(e[r]);
                return o(t.quickExportSort(i), [e[n]], t.quickExportSort(a))
            }
            ,
            t.scrollViewOneItem = function(t, e) {
                if (void 0 === e && (e = "v"),
                !(t.isScrolling() || t.isAutoScrolling() || t.content.childrenCount <= 0)) {
                    var n = t.uuid
                      , o = (t.content.childrenCount,
                    0)
                      , i = 0
                      , a = t.content.getComponent(cc.Layout);
                    if (a && ("h" === e ? (i = t.content.children[0].width + a.spacingX,
                    t.node.width,
                    o = t.getMaxScrollOffset().x,
                    Math.round(t.content.height / (t.content.children[0].height + a.spacingY))) : (i = t.content.children[0].height + a.spacingY,
                    t.node.height,
                    o = t.getMaxScrollOffset().y,
                    Math.round(t.content.width / (t.content.children[0].width + a.spacingX))),
                    !(o <= 0))) {
                        var r = t.getScrollOffset().x
                          , c = t.getScrollOffset().y;
                        "h" === e && (c = -t.getScrollOffset().x,
                        r = t.getScrollOffset().y),
                        c + i > o ? this._scrollViewData[n] = "prev" : c < i && (this._scrollViewData[n] = "next"),
                        this._scrollViewData.hasOwnProperty(n) || (this._scrollViewData[n] = "next");
                        var s = null;
                        s = "next" == this._scrollViewData[n] ? "h" === e ? cc.v2(i + c, r) : cc.v2(r, i + c) : "h" === e ? cc.v2(c - i, r) : cc.v2(r, c - i),
                        "h" === e ? 0 !== s.x && (s.x = Math.round(s.x / i) * i) : 0 !== s.y && (s.y = Math.round(s.y / i) * i),
                        t.scrollToOffset(s, .5)
                    }
                }
            }
            ,
            t.checkPer = function(e) {
                return !!e && t.getRandomMax(100) <= e
            }
            ,
            t.handleVideo = function() {
                return new Promise(function(t) {
					show_RewardVideo();
					window.rewardt=t;
                   // window.h5api.playAd(function(e) {
                     //   1e4 === e.code ? console.log("kaishi bofang shipin ") : 10001 === e.code ? (console.log("bofang chenggong "),
                       // t(!0)) : (t(!1),
                        //console.log("bofang shibai"))
                    //})
                }
                )
            }
            ,
            t.getCollision = function(t, e, n) {
                void 0 === t && (t = !0),
                void 0 === e && (e = !1),
                void 0 === n && (n = !1);
                var o = cc.director.getCollisionManager();
                o.enabled = t,
                o.enabledDebugDraw = e,
                o.enabledDrawBoundingBox = n
            }
            ,
            t.getPhysics = function(t, e) {
                void 0 === t && (t = !0),
                void 0 === e && (e = !1),
                cc.director.getPhysicsManager().enabled = !0,
                e && (cc.director.getPhysicsManager().debugDrawFlags = cc.PhysicsManager.DrawBits.e_aabbBit | cc.PhysicsManager.DrawBits.e_jointBit | cc.PhysicsManager.DrawBits.e_shapeBit)
            }
            ,
            t.onTouchAll = function(t, e, n, o, i, a, r) {
                void 0 === r && (r = !0),
                t && (r ? (t.on(cc.Node.EventType.TOUCH_START, e, a),
                t.on(cc.Node.EventType.TOUCH_MOVE, n, a),
                t.on(cc.Node.EventType.TOUCH_END, o, a),
                t.on(cc.Node.EventType.TOUCH_CANCEL, i, a)) : (t.off(cc.Node.EventType.TOUCH_START, e, a),
                t.off(cc.Node.EventType.TOUCH_MOVE, n, a),
                t.off(cc.Node.EventType.TOUCH_END, o, a),
                t.off(cc.Node.EventType.TOUCH_CANCEL, i, a)))
            }
            ,
            t.getChildrenIndex = function(t) {
                for (var e = t.parent, n = 0; n < e.children.length; n++)
                    if (t === e.children[n])
                        return n
            }
            ,
            t.getPointInNode = function(t, e) {
                return e.getBoundingBoxToWorld().contains(t)
            }
            ,
            t.date_getTimeNum = function(t) {
                return 1e4 * t.getFullYear() + 100 * (t.getMonth() + 1) + t.getDate()
            }
            ,
            t.date_getTimeDifference = function(t, e, n) {
                if (l(t) && (t = new Date(t)),
                l(e) && (e = new Date(e)),
                t instanceof Date && e instanceof Date) {
                    var o = t.getTime() - e.getTime();
                    switch (n) {
                    case 0:
                        return {
                            distance: Math.floor(o / 864e5),
                            distance_real: o
                        };
                    case 1:
                        return {
                            distance: Math.floor(o / 36e5),
                            distance_real: o
                        };
                    case 2:
                        return {
                            distance: Math.floor(o / 6e4),
                            distance_real: o
                        };
                    case 3:
                        return {
                            distance: Math.floor(o / 1e3),
                            distance_real: o
                        }
                    }
                }
            }
            ,
            t.getNodeForRect = function(t) {
                var e = new cc.Node;
                return e.width = t.width,
                e.height = t.height,
                e.setPosition(cc.v3(t.center)),
                e
            }
            ,
            t.getNodeFourPoint = function(t) {
                var e = t.getAnchorPoint();
                return {
                    left_down: cc.v2(t.position.x - e.x * t.width, t.position.y - e.y * t.height),
                    left_top: cc.v2(t.position.x - e.x * t.width, t.position.y + (1 - e.y) * t.height),
                    right_down: cc.v2(t.position.x + (1 - e.x) * t.width, t.position.y - e.y * t.height),
                    right_top: cc.v2(t.position.x + (1 - e.x) * t.width, t.position.y + (1 - e.y) * t.height)
                }
            }
            ,
            t.judgeValueInArr = function(t, e) {
                for (var n = !1, o = 0; o < e.length; o++)
                    if (e[o] === t) {
                        n = !0;
                        break
                    }
                return n
            }
            ,
            t.judgeArraySame = function(t, e) {
                for (var n = !1, o = 0; o < t.length; o++)
                    for (var i = 0; i < e.length; i++)
                        if (t[o] == e[i])
                            return !0;
                return n
            }
            ,
            t.model_initModel = function(t) {
                for (var e = [function() {
                    i.default.loadBundle(["sub"]).then(function() {
                        t()
                    })
                }
                ], n = 0; n < e.length; n++)
                    e[n]();
                return e.length
            }
            ,
            t.changeStamina = function(t, e) {
                return a.default.stamina + t < 0 ? (c.default.INS.openPanel({
                    panel: s.default,
                    layer: c.Layer.gameLayer,
                    param: {
                        type: "stamina",
                        callBack: e,
                        price: Math.abs(t)
                    }
                }),
                !1) : (e && e(),
                a.default.stamina = a.default.stamina + t,
                !0)
            }
            ,
            t.changeGold = function(t, e) {
                return a.default.gold + t < 0 ? (c.default.INS.openPanel({
                    panel: s.default,
                    layer: c.Layer.gameLayer,
                    param: {
                        type: "gold",
                        callBack: e,
                        price: Math.abs(t)
                    }
                }),
                !1) : (e && e(),
                a.default.gold = a.default.gold + t,
                !0)
            }
            ,
            t._scrollViewData = {},
            t
        }();
        n.default = u,
        cc._RF.pop()
    }
    , {
        "../Moudle/View/ShortageView": "ShortageView",
        "./manage/CacheMgr": "CacheMgr",
        "./manage/GameLogMgr": "GameLogMgr",
        "./manage/LoadMgr": "LoadMgr",
        "./manage/PanelMgr": "PanelMgr"
    }],
    gameConfig: [function(t, e, n) {
        "use strict";
        cc._RF.push(e, "9fcd3ropcJAfa0NldI9i+rX", "gameConfig"),
        Object.defineProperty(n, "__esModule", {
            value: !0
        });
        var o = function() {
            function t() {}
            return t.width_max_num = 8,
            t.height_max_num = 9,
            t.max_Item = 20,
            t.coinsChangeTime = 1,
            t.extra = 30,
            t.allFrame = 40,
            t.allLevel = 50,
            t.level_all_time = 180,
            t.level_tier = [5, 3, 4, 3, 2, 1, 5, 3, 4, 4, 3, 5, 3, 3, 2, 5, 2, 3, 1, 3, 2, 2, 3, 3, 2, 1, 2, 1, 2, 3, 3, 2, 1, 4, 3, 2, 3, 3, 2, 5, 2, 3, 3, 3, 2, 3, 3, 2, 3, 3],
            t.level_data2 = [{
                tier: [3],
                data: [[0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0]],
                limit: 2
            }, {
                tier: [5],
                data: [[0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0]],
                limit: 3
            }, {
                tier: [4, 5],
                data: [[0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0]],
                limit: 3
            }, {
                tier: [5],
                data: [[1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0]],
                limit: 2
            }, {
                tier: [6],
                data: [[0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0]],
                limit: 3
            }, {
                tier: [7],
                data: [[0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]],
                limit: 3
            }, {
                tier: [3, 4],
                data: [[0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0], [0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0]],
                limit: 3
            }, {
                tier: [5, 6],
                data: [[0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0], [0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0]],
                limit: 3
            }, {
                tier: [4, 5],
                data: [[1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]],
                limit: 4
            }, {
                tier: [4, 5],
                data: [[0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0], [0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0]],
                limit: 4
            }, {
                tier: [5, 6],
                data: [[0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0], [0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0]],
                limit: 4
            }, {
                tier: [3, 4, 5, 6],
                data: [[0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]],
                limit: 4
            }, {
                tier: [5, 6],
                data: [[1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0], [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0]],
                limit: 4
            }, {
                tier: [5, 6],
                data: [[1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1]],
                limit: 5
            }, {
                tier: [6, 7],
                data: [[1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1], [1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 5]],
                limit: 5
            }, {
                tier: [3, 4, 5],
                data: [[1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1], [1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1]],
                limit: 6
            }, {
                tier: [6, 7],
                data: [[1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1]],
                limit: 5
            }, {
                tier: [5, 6, 7],
                data: [[1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0], [1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0], [1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0]],
                limit: 5
            }, {
                tier: [7, 8],
                data: [[0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0]],
                limit: 5
            }, {
                tier: [5, 6, 7],
                data: [[1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], [1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], [1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]],
                limit: 6
            }, {
                tier: [6, 7, 8],
                data: [[0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0], [0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0], [0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0]],
                limit: 6
            }, {
                tier: [6, 7, 8],
                data: [[0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0]],
                limit: 6
            }, {
                tier: [5, 6],
                data: [[1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1], [1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1]],
                limit: 4
            }, {
                tier: [5, 6],
                data: [[0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1], [0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]],
                limit: 4
            }, {
                tier: [6, 7],
                data: [[0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0]],
                limit: 7
            }, {
                tier: [7, 8],
                data: [[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]],
                limit: 8
            }, {
                tier: [6, 7],
                data: [[0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0]],
                limit: 9
            }, {
                tier: [7, 8],
                data: [[0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0]],
                limit: 7
            }, {
                tier: [6, 7, 8],
                data: [[0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0], [0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0], [0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0]],
                limit: 6
            }, {
                tier: [5, 6, 7],
                data: [[1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1], [1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1], [1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1]],
                limit: 6
            }, {
                tier: [5, 6, 7, 8],
                data: [[1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1], [1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1], [1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1], [1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0]],
                limit: 6
            }, {
                tier: [6, 7],
                data: [[0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]],
                limit: 7
            }, {
                tier: [7, 8],
                data: [[0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0]],
                limit: 8
            }, {
                tier: [4, 5, 6, 7],
                data: [[0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0], [0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0], [0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0], [0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0]],
                limit: 7
            }, {
                tier: [5, 6, 7],
                data: [[0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0], [0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0]],
                limit: 7
            }, {
                tier: [6, 7, 8],
                data: [[0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0]],
                limit: 10
            }, {
                tier: [5, 6, 7],
                data: [[1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0], [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0], [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0]],
                limit: 12
            }, {
                tier: [5, 6, 7, 8],
                data: [[1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1]],
                limit: 10
            }, {
                tier: [6, 7, 8],
                data: [[1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1], [1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1], [1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1]],
                limit: 10
            }, {
                tier: [3, 4, 5, 6, 7],
                data: [[1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1], [1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1]],
                limit: 11
            }, {
                tier: [6, 7, 8],
                data: [[1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0], [1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0], [1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0]],
                limit: 11
            }, {
                tier: [5, 6, 7],
                data: [[0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0], [0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0]],
                limit: 11
            }, {
                tier: [5, 6, 7, 8],
                data: [[1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0], [1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0], [1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0], [1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0]],
                limit: 12
            }, {
                tier: [5, 6, 7, 8],
                data: [[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0]],
                limit: 11
            }, {
                tier: [6, 7, 8],
                data: [[0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0], [0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0], [0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0]],
                limit: 11
            }, {
                tier: [5, 6, 7],
                data: [[0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0]],
                limit: 11
            }, {
                tier: [5, 6, 7, 8],
                data: [[0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0], [0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0], [0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0], [0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0]],
                limit: 11
            }, {
                tier: [6, 7],
                data: [[0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0], [0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0]],
                limit: 11
            }, {
                tier: [5, 6, 7],
                data: [[1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1], [1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1], [1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1]],
                limit: 11
            }, {
                tier: [5, 6, 7],
                data: [[1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1], [1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1], [1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1]],
                limit: 11
            }],
            t.all_item_position = [],
            t.shop_price = [300, 3e3, 300, 3e3],
            t.shop_propCount = [{
                title: "recall",
                count: 1
            }, {
                title: "recall",
                count: 11
            }, {
                title: "hint",
                count: 1
            }, {
                title: "hint",
                count: 11
            }],
            t.signInData = [50, 100, 150, 200, 250, 300],
            t.prize = {
                type: "hint",
                count: 5
            },
            t.isDoubleTime = !1,
            t
        }();
        n.default = o,
        cc._RF.pop()
    }
    , {}]
}, {}, ["Constant", "Global", "Test", "Tools", "ActionMgr", "AudioMgr", "CacheMgr", "Emit", "EmitBase", "EmitData", "GameLogMgr", "LayerMgr", "LayerPanel", "LayerUI", "LoadMgr", "PanelMgr", "PondMgr", "StorageMgr", "TimerMgr", "ToastTips", "Item", "SettingBox", "Shop", "SignIn", "gameConfig", "EndView", "GameInfoView", "GameView", "HomeView", "ShortageView", "Game", "Loading"]);
function show_nativead(){
	android.doJava("insert_xxx");
}

function show_RewardVideo(){
	 android.doJava("reward_do");
}

function callOnReward(){
	window.rewardt(!0);
}