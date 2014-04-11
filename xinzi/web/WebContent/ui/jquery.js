/*
 * jQuery JavaScript Library v1.6.3
 * http://jquery.com/
 *
 * Copyright 2011, John Resig
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * Includes Sizzle.js
 * http://sizzlejs.com/
 * Copyright 2011, The Dojo Foundation
 * Released under the MIT, BSD, and GPL Licenses.
 *
 * Date: Wed Aug 31 10:35:15 2011 -0400
 */
(function(a7, K) {
	var ap = a7.document, bq = a7.navigator, bh = a7.location;
	var b = (function() {
		var bB = function(bX, bY) {
			return new bB.fn.init(bX, bY, bz)
		}, bR = a7.jQuery, bD = a7.$, bz, bV = /^(?:[^#<]*(<[\w\W]+>)[^>]*$|#([\w\-]*)$)/, bJ = /\S/, bF = /^\s+/, bA = /\s+$/, bE = /\d/, bw = /^<(\w+)\s*\/?>(?:<\/\1>)?$/, bK = /^[\],:{}\s]*$/, bT = /\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, bM = /"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, bG = /(?:^|:|,)(?:\s*\[)+/g, bu = /(webkit)[ \/]([\w.]+)/, bO = /(opera)(?:.*version)?[ \/]([\w.]+)/, bN = /(msie) ([\w.]+)/, bP = /(mozilla)(?:.*? rv:([\w.]+))?/, bx = /-([a-z]|[0-9])/ig, bW = /^-ms-/, bQ = function(
				bX, bY) {
			return (bY + "").toUpperCase()
		}, bU = bq.userAgent, bS, by, e, bI = Object.prototype.toString, bC = Object.prototype.hasOwnProperty, bv = Array.prototype.push, bH = Array.prototype.slice, bL = String.prototype.trim, br = Array.prototype.indexOf, bt = {};
		bB.fn = bB.prototype = {
			constructor : bB,
			init : function(bX, b1, b0) {
				var bZ, b2, bY, b3;
				if (!bX) {
					return this
				}
				if (bX.nodeType) {
					this.context = this[0] = bX;
					this.length = 1;
					return this
				}
				if (bX === "body" && !b1 && ap.body) {
					this.context = ap;
					this[0] = ap.body;
					this.selector = bX;
					this.length = 1;
					return this
				}
				if (typeof bX === "string") {
					if (bX.charAt(0) === "<"
							&& bX.charAt(bX.length - 1) === ">"
							&& bX.length >= 3) {
						bZ = [ null, bX, null ]
					} else {
						bZ = bV.exec(bX)
					}
					if (bZ && (bZ[1] || !b1)) {
						if (bZ[1]) {
							b1 = b1 instanceof bB ? b1[0] : b1;
							b3 = (b1 ? b1.ownerDocument || b1 : ap);
							bY = bw.exec(bX);
							if (bY) {
								if (bB.isPlainObject(b1)) {
									bX = [ ap.createElement(bY[1]) ];
									bB.fn.attr.call(bX, b1, true)
								} else {
									bX = [ b3.createElement(bY[1]) ]
								}
							} else {
								bY = bB.buildFragment( [ bZ[1] ], [ b3 ]);
								bX = (bY.cacheable ? bB.clone(bY.fragment)
										: bY.fragment).childNodes
							}
							return bB.merge(this, bX)
						} else {
							b2 = ap.getElementById(bZ[2]);
							if (b2 && b2.parentNode) {
								if (b2.id !== bZ[2]) {
									return b0.find(bX)
								}
								this.length = 1;
								this[0] = b2
							}
							this.context = ap;
							this.selector = bX;
							return this
						}
					} else {
						if (!b1 || b1.jquery) {
							return (b1 || b0).find(bX)
						} else {
							return this.constructor(b1).find(bX)
						}
					}
				} else {
					if (bB.isFunction(bX)) {
						return b0.ready(bX)
					}
				}
				if (bX.selector !== K) {
					this.selector = bX.selector;
					this.context = bX.context
				}
				return bB.makeArray(bX, this)
			},
			selector : "",
			jquery : "1.6.3",
			length : 0,
			size : function() {
				return this.length
			},
			toArray : function() {
				return bH.call(this, 0)
			},
			get : function(bX) {
				return bX == null ? this.toArray() : (bX < 0 ? this[this.length
						+ bX] : this[bX])
			},
			pushStack : function(bY, b0, bX) {
				var bZ = this.constructor();
				if (bB.isArray(bY)) {
					bv.apply(bZ, bY)
				} else {
					bB.merge(bZ, bY)
				}
				bZ.prevObject = this;
				bZ.context = this.context;
				if (b0 === "find") {
					bZ.selector = this.selector + (this.selector ? " " : "")
							+ bX
				} else {
					if (b0) {
						bZ.selector = this.selector + "." + b0 + "(" + bX + ")"
					}
				}
				return bZ
			},
			each : function(bY, bX) {
				return bB.each(this, bY, bX)
			},
			ready : function(bX) {
				bB.bindReady();
				by.done(bX);
				return this
			},
			eq : function(bX) {
				return bX === -1 ? this.slice(bX) : this.slice(bX, +bX + 1)
			},
			first : function() {
				return this.eq(0)
			},
			last : function() {
				return this.eq(-1)
			},
			slice : function() {
				return this.pushStack(bH.apply(this, arguments), "slice", bH
						.call(arguments).join(","))
			},
			map : function(bX) {
				return this.pushStack(bB.map(this, function(bZ, bY) {
					return bX.call(bZ, bY, bZ)
				}))
			},
			end : function() {
				return this.prevObject || this.constructor(null)
			},
			push : bv,
			sort : [].sort,
			splice : [].splice
		};
		bB.fn.init.prototype = bB.fn;
		bB.extend = bB.fn.extend = function() {
			var b6, bZ, bX, bY, b3, b4, b2 = arguments[0] || {}, b1 = 1, b0 = arguments.length, b5 = false;
			if (typeof b2 === "boolean") {
				b5 = b2;
				b2 = arguments[1] || {};
				b1 = 2
			}
			if (typeof b2 !== "object" && !bB.isFunction(b2)) {
				b2 = {}
			}
			if (b0 === b1) {
				b2 = this;
				--b1
			}
			for (; b1 < b0; b1++) {
				if ((b6 = arguments[b1]) != null) {
					for (bZ in b6) {
						bX = b2[bZ];
						bY = b6[bZ];
						if (b2 === bY) {
							continue
						}
						if (b5
								&& bY
								&& (bB.isPlainObject(bY) || (b3 = bB
										.isArray(bY)))) {
							if (b3) {
								b3 = false;
								b4 = bX && bB.isArray(bX) ? bX : []
							} else {
								b4 = bX && bB.isPlainObject(bX) ? bX : {}
							}
							b2[bZ] = bB.extend(b5, b4, bY)
						} else {
							if (bY !== K) {
								b2[bZ] = bY
							}
						}
					}
				}
			}
			return b2
		};
		bB
				.extend( {
					noConflict : function(bX) {
						if (a7.$ === bB) {
							a7.$ = bD
						}
						if (bX && a7.jQuery === bB) {
							a7.jQuery = bR
						}
						return bB
					},
					isReady : false,
					readyWait : 1,
					holdReady : function(bX) {
						if (bX) {
							bB.readyWait++
						} else {
							bB.ready(true)
						}
					},
					ready : function(bX) {
						if ((bX === true && !--bB.readyWait)
								|| (bX !== true && !bB.isReady)) {
							if (!ap.body) {
								return setTimeout(bB.ready, 1)
							}
							bB.isReady = true;
							if (bX !== true && --bB.readyWait > 0) {
								return
							}
							by.resolveWith(ap, [ bB ]);
							if (bB.fn.trigger) {
								bB(ap).trigger("ready").unbind("ready")
							}
						}
					},
					bindReady : function() {
						if (by) {
							return
						}
						by = bB._Deferred();
						if (ap.readyState === "complete") {
							return setTimeout(bB.ready, 1)
						}
						if (ap.addEventListener) {
							ap.addEventListener("DOMContentLoaded", e, false);
							a7.addEventListener("load", bB.ready, false)
						} else {
							if (ap.attachEvent) {
								ap.attachEvent("onreadystatechange", e);
								a7.attachEvent("onload", bB.ready);
								var bX = false;
								try {
									bX = a7.frameElement == null
								} catch (bY) {
								}
								if (ap.documentElement.doScroll && bX) {
									bs()
								}
							}
						}
					},
					isFunction : function(bX) {
						return bB.type(bX) === "function"
					},
					isArray : Array.isArray || function(bX) {
						return bB.type(bX) === "array"
					},
					isWindow : function(bX) {
						return bX && typeof bX === "object"
								&& "setInterval" in bX
					},
					isNaN : function(bX) {
						return bX == null || !bE.test(bX) || isNaN(bX)
					},
					type : function(bX) {
						return bX == null ? String(bX) : bt[bI.call(bX)]
								|| "object"
					},
					isPlainObject : function(bZ) {
						if (!bZ || bB.type(bZ) !== "object" || bZ.nodeType
								|| bB.isWindow(bZ)) {
							return false
						}
						try {
							if (bZ.constructor
									&& !bC.call(bZ, "constructor")
									&& !bC.call(bZ.constructor.prototype,
											"isPrototypeOf")) {
								return false
							}
						} catch (bY) {
							return false
						}
						var bX;
						for (bX in bZ) {
						}
						return bX === K || bC.call(bZ, bX)
					},
					isEmptyObject : function(bY) {
						for ( var bX in bY) {
							return false
						}
						return true
					},
					error : function(bX) {
						throw bX
					},
					parseJSON : function(bX) {
						if (typeof bX !== "string" || !bX) {
							return null
						}
						bX = bB.trim(bX);
						if (a7.JSON && a7.JSON.parse) {
							return a7.JSON.parse(bX)
						}
						if (bK.test(bX.replace(bT, "@").replace(bM, "]")
								.replace(bG, ""))) {
							return (new Function("return " + bX))()
						}
						bB.error("Invalid JSON: " + bX)
					},
					parseXML : function(bZ) {
						var bX, bY;
						try {
							if (a7.DOMParser) {
								bY = new DOMParser();
								bX = bY.parseFromString(bZ, "text/xml")
							} else {
								bX = new ActiveXObject("Microsoft.XMLDOM");
								bX.async = "false";
								bX.loadXML(bZ)
							}
						} catch (b0) {
							bX = K
						}
						if (!bX
								|| !bX.documentElement
								|| bX.getElementsByTagName("parsererror").length) {
							bB.error("Invalid XML: " + bZ)
						}
						return bX
					},
					noop : function() {
					},
					globalEval : function(bX) {
						if (bX && bJ.test(bX)) {
							(a7.execScript || function(bY) {
								a7["eval"].call(a7, bY)
							})(bX)
						}
					},
					camelCase : function(bX) {
						return bX.replace(bW, "ms-").replace(bx, bQ)
					},
					nodeName : function(bY, bX) {
						return bY.nodeName
								&& bY.nodeName.toUpperCase() === bX
										.toUpperCase()
					},
					each : function(b0, b3, bZ) {
						var bY, b1 = 0, b2 = b0.length, bX = b2 === K
								|| bB.isFunction(b0);
						if (bZ) {
							if (bX) {
								for (bY in b0) {
									if (b3.apply(b0[bY], bZ) === false) {
										break
									}
								}
							} else {
								for (; b1 < b2;) {
									if (b3.apply(b0[b1++], bZ) === false) {
										break
									}
								}
							}
						} else {
							if (bX) {
								for (bY in b0) {
									if (b3.call(b0[bY], bY, b0[bY]) === false) {
										break
									}
								}
							} else {
								for (; b1 < b2;) {
									if (b3.call(b0[b1], b1, b0[b1++]) === false) {
										break
									}
								}
							}
						}
						return b0
					},
					trim : bL ? function(bX) {
						return bX == null ? "" : bL.call(bX)
					} : function(bX) {
						return bX == null ? "" : bX.toString().replace(bF, "")
								.replace(bA, "")
					},
					makeArray : function(b0, bY) {
						var bX = bY || [];
						if (b0 != null) {
							var bZ = bB.type(b0);
							if (b0.length == null || bZ === "string"
									|| bZ === "function" || bZ === "regexp"
									|| bB.isWindow(b0)) {
								bv.call(bX, b0)
							} else {
								bB.merge(bX, b0)
							}
						}
						return bX
					},
					inArray : function(bZ, b0) {
						if (!b0) {
							return -1
						}
						if (br) {
							return br.call(b0, bZ)
						}
						for ( var bX = 0, bY = b0.length; bX < bY; bX++) {
							if (b0[bX] === bZ) {
								return bX
							}
						}
						return -1
					},
					merge : function(b1, bZ) {
						var b0 = b1.length, bY = 0;
						if (typeof bZ.length === "number") {
							for ( var bX = bZ.length; bY < bX; bY++) {
								b1[b0++] = bZ[bY]
							}
						} else {
							while (bZ[bY] !== K) {
								b1[b0++] = bZ[bY++]
							}
						}
						b1.length = b0;
						return b1
					},
					grep : function(bY, b3, bX) {
						var bZ = [], b2;
						bX = !!bX;
						for ( var b0 = 0, b1 = bY.length; b0 < b1; b0++) {
							b2 = !!b3(bY[b0], b0);
							if (bX !== b2) {
								bZ.push(bY[b0])
							}
						}
						return bZ
					},
					map : function(bX, b4, b5) {
						var b2, b3, b1 = [], bZ = 0, bY = bX.length, b0 = bX instanceof bB
								|| bY !== K
								&& typeof bY === "number"
								&& ((bY > 0 && bX[0] && bX[bY - 1]) || bY === 0 || bB
										.isArray(bX));
						if (b0) {
							for (; bZ < bY; bZ++) {
								b2 = b4(bX[bZ], bZ, b5);
								if (b2 != null) {
									b1[b1.length] = b2
								}
							}
						} else {
							for (b3 in bX) {
								b2 = b4(bX[b3], b3, b5);
								if (b2 != null) {
									b1[b1.length] = b2
								}
							}
						}
						return b1.concat.apply( [], b1)
					},
					guid : 1,
					proxy : function(b1, b0) {
						if (typeof b0 === "string") {
							var bZ = b1[b0];
							b0 = b1;
							b1 = bZ
						}
						if (!bB.isFunction(b1)) {
							return K
						}
						var bX = bH.call(arguments, 2), bY = function() {
							return b1.apply(b0, bX.concat(bH.call(arguments)))
						};
						bY.guid = b1.guid = b1.guid || bY.guid || bB.guid++;
						return bY
					},
					access : function(bX, b5, b3, bZ, b2, b4) {
						var bY = bX.length;
						if (typeof b5 === "object") {
							for ( var b0 in b5) {
								bB.access(bX, b0, b5[b0], bZ, b2, b3)
							}
							return bX
						}
						if (b3 !== K) {
							bZ = !b4 && bZ && bB.isFunction(b3);
							for ( var b1 = 0; b1 < bY; b1++) {
								b2(bX[b1], b5, bZ ? b3.call(bX[b1], b1, b2(
										bX[b1], b5)) : b3, b4)
							}
							return bX
						}
						return bY ? b2(bX[0], b5) : K
					},
					now : function() {
						return (new Date()).getTime()
					},
					uaMatch : function(bY) {
						bY = bY.toLowerCase();
						var bX = bu.exec(bY) || bO.exec(bY) || bN.exec(bY)
								|| bY.indexOf("compatible") < 0 && bP.exec(bY)
								|| [];
						return {
							browser : bX[1] || "",
							version : bX[2] || "0"
						}
					},
					sub : function() {
						function bX(b0, b1) {
							return new bX.fn.init(b0, b1)
						}
						bB.extend(true, bX, this);
						bX.superclass = this;
						bX.fn = bX.prototype = this();
						bX.fn.constructor = bX;
						bX.sub = this.sub;
						bX.fn.init = function bZ(b0, b1) {
							if (b1 && b1 instanceof bB && !(b1 instanceof bX)) {
								b1 = bX(b1)
							}
							return bB.fn.init.call(this, b0, b1, bY)
						};
						bX.fn.init.prototype = bX.fn;
						var bY = bX(ap);
						return bX
					},
					browser : {}
				});
		bB.each("Boolean Number String Function Array Date RegExp Object"
				.split(" "), function(bY, bX) {
			bt["[object " + bX + "]"] = bX.toLowerCase()
		});
		bS = bB.uaMatch(bU);
		if (bS.browser) {
			bB.browser[bS.browser] = true;
			bB.browser.version = bS.version
		}
		if (bB.browser.webkit) {
			bB.browser.safari = true
		}
		if (bJ.test("\xA0")) {
			bF = /^[\s\xA0]+/;
			bA = /[\s\xA0]+$/
		}
		bz = bB(ap);
		if (ap.addEventListener) {
			e = function() {
				ap.removeEventListener("DOMContentLoaded", e, false);
				bB.ready()
			}
		} else {
			if (ap.attachEvent) {
				e = function() {
					if (ap.readyState === "complete") {
						ap.detachEvent("onreadystatechange", e);
						bB.ready()
					}
				}
			}
		}
		function bs() {
			if (bB.isReady) {
				return
			}
			try {
				ap.documentElement.doScroll("left")
			} catch (bX) {
				setTimeout(bs, 1);
				return
			}
			bB.ready()
		}
		return bB
	})();
	var a = "done fail isResolved isRejected promise then always pipe"
			.split(" "), aE = [].slice;
	b
			.extend( {
				_Deferred : function() {
					var bt = [], bu, br, bs, e = {
						done : function() {
							if (!bs) {
								var bw = arguments, bx, bA, bz, by, bv;
								if (bu) {
									bv = bu;
									bu = 0
								}
								for (bx = 0, bA = bw.length; bx < bA; bx++) {
									bz = bw[bx];
									by = b.type(bz);
									if (by === "array") {
										e.done.apply(e, bz)
									} else {
										if (by === "function") {
											bt.push(bz)
										}
									}
								}
								if (bv) {
									e.resolveWith(bv[0], bv[1])
								}
							}
							return this
						},
						resolveWith : function(bw, bv) {
							if (!bs && !bu && !br) {
								bv = bv || [];
								br = 1;
								try {
									while (bt[0]) {
										bt.shift().apply(bw, bv)
									}
								} finally {
									bu = [ bw, bv ];
									br = 0
								}
							}
							return this
						},
						resolve : function() {
							e.resolveWith(this, arguments);
							return this
						},
						isResolved : function() {
							return !!(br || bu)
						},
						cancel : function() {
							bs = 1;
							bt = [];
							return this
						}
					};
					return e
				},
				Deferred : function(br) {
					var e = b._Deferred(), bt = b._Deferred(), bs;
					b
							.extend(
									e,
									{
										then : function(bv, bu) {
											e.done(bv).fail(bu);
											return this
										},
										always : function() {
											return e.done.apply(e, arguments).fail
													.apply(this, arguments)
										},
										fail : bt.done,
										rejectWith : bt.resolveWith,
										reject : bt.resolve,
										isRejected : bt.isResolved,
										pipe : function(bv, bu) {
											return b
													.Deferred(
															function(bw) {
																b
																		.each(
																				{
																					done : [
																							bv,
																							"resolve" ],
																					fail : [
																							bu,
																							"reject" ]
																				},
																				function(
																						by,
																						bB) {
																					var bx = bB[0], bA = bB[1], bz;
																					if (b
																							.isFunction(bx)) {
																						e[by]
																								(function() {
																									bz = bx
																											.apply(
																													this,
																													arguments);
																									if (bz
																											&& b
																													.isFunction(bz.promise)) {
																										bz
																												.promise()
																												.then(
																														bw.resolve,
																														bw.reject)
																									} else {
																										bw[bA
																												+ "With"]
																												(
																														this === e ? bw
																																: this,
																														[ bz ])
																									}
																								})
																					} else {
																						e[by]
																								(bw[bA])
																					}
																				})
															}).promise()
										},
										promise : function(bv) {
											if (bv == null) {
												if (bs) {
													return bs
												}
												bs = bv = {}
											}
											var bu = a.length;
											while (bu--) {
												bv[a[bu]] = e[a[bu]]
											}
											return bv
										}
									});
					e.done(bt.cancel).fail(e.cancel);
					delete e.cancel;
					if (br) {
						br.call(e, e)
					}
					return e
				},
				when : function(bw) {
					var br = arguments, bs = 0, bv = br.length, bu = bv, e = bv <= 1
							&& bw && b.isFunction(bw.promise) ? bw : b
							.Deferred();
					function bt(bx) {
						return function(by) {
							br[bx] = arguments.length > 1 ? aE.call(arguments,
									0) : by;
							if (!(--bu)) {
								e.resolveWith(e, aE.call(br, 0))
							}
						}
					}
					if (bv > 1) {
						for (; bs < bv; bs++) {
							if (br[bs] && b.isFunction(br[bs].promise)) {
								br[bs].promise().then(bt(bs), e.reject)
							} else {
								--bu
							}
						}
						if (!bu) {
							e.resolveWith(e, br)
						}
					} else {
						if (e !== bw) {
							e.resolveWith(e, bv ? [ bw ] : [])
						}
					}
					return e.promise()
				}
			});
	b.support = (function() {
		var bB = ap.createElement("div"), bI = ap.documentElement, bu, bJ, bC, bs, bA, bv, by, br, bz, bD, bx, bH, bF, bt, bw, bE, bK;
		bB.setAttribute("className", "t");
		bB.innerHTML = "   <link><table></table><a href='/a' style='top:1px;float:left;opacity:.55;'>a</a><input type=checkbox>";
		bu = bB.getElementsByTagName("*");
		bJ = bB.getElementsByTagName("a")[0];
		if (!bu || !bu.length || !bJ) {
			return {}
		}
		bC = ap.createElement("select");
		bs = bC.appendChild(ap.createElement("option"));
		bA = bB.getElementsByTagName("input")[0];
		by = {
			leadingWhitespace : (bB.firstChild.nodeType === 3),
			tbody : !bB.getElementsByTagName("tbody").length,
			htmlSerialize : !!bB.getElementsByTagName("link").length,
			style : /top/.test(bJ.getAttribute("style")),
			hrefNormalized : (bJ.getAttribute("href") === "/a"),
			opacity : /^0.55$/.test(bJ.style.opacity),
			cssFloat : !!bJ.style.cssFloat,
			checkOn : (bA.value === "on"),
			optSelected : bs.selected,
			getSetAttribute : bB.className !== "t",
			submitBubbles : true,
			changeBubbles : true,
			focusinBubbles : false,
			deleteExpando : true,
			noCloneEvent : true,
			inlineBlockNeedsLayout : false,
			shrinkWrapBlocks : false,
			reliableMarginRight : true
		};
		bA.checked = true;
		by.noCloneChecked = bA.cloneNode(true).checked;
		bC.disabled = true;
		by.optDisabled = !bs.disabled;
		try {
			delete bB.test
		} catch (bG) {
			by.deleteExpando = false
		}
		if (!bB.addEventListener && bB.attachEvent && bB.fireEvent) {
			bB.attachEvent("onclick", function() {
				by.noCloneEvent = false
			});
			bB.cloneNode(true).fireEvent("onclick")
		}
		bA = ap.createElement("input");
		bA.value = "t";
		bA.setAttribute("type", "radio");
		by.radioValue = bA.value === "t";
		bA.setAttribute("checked", "checked");
		bB.appendChild(bA);
		br = ap.createDocumentFragment();
		br.appendChild(bB.firstChild);
		by.checkClone = br.cloneNode(true).cloneNode(true).lastChild.checked;
		bB.innerHTML = "";
		bB.style.width = bB.style.paddingLeft = "1px";
		bz = ap.getElementsByTagName("body")[0];
		bx = ap.createElement(bz ? "div" : "body");
		bH = {
			visibility : "hidden",
			width : 0,
			height : 0,
			border : 0,
			margin : 0,
			background : "none"
		};
		if (bz) {
			b.extend(bH, {
				position : "absolute",
				left : "-1000px",
				top : "-1000px"
			})
		}
		for (bE in bH) {
			bx.style[bE] = bH[bE]
		}
		bx.appendChild(bB);
		bD = bz || bI;
		bD.insertBefore(bx, bD.firstChild);
		by.appendChecked = bA.checked;
		by.boxModel = bB.offsetWidth === 2;
		if ("zoom" in bB.style) {
			bB.style.display = "inline";
			bB.style.zoom = 1;
			by.inlineBlockNeedsLayout = (bB.offsetWidth === 2);
			bB.style.display = "";
			bB.innerHTML = "<div style='width:4px;'></div>";
			by.shrinkWrapBlocks = (bB.offsetWidth !== 2)
		}
		bB.innerHTML = "<table><tr><td style='padding:0;border:0;display:none'></td><td>t</td></tr></table>";
		bF = bB.getElementsByTagName("td");
		bK = (bF[0].offsetHeight === 0);
		bF[0].style.display = "";
		bF[1].style.display = "none";
		by.reliableHiddenOffsets = bK && (bF[0].offsetHeight === 0);
		bB.innerHTML = "";
		if (ap.defaultView && ap.defaultView.getComputedStyle) {
			bv = ap.createElement("div");
			bv.style.width = "0";
			bv.style.marginRight = "0";
			bB.appendChild(bv);
			by.reliableMarginRight = (parseInt((ap.defaultView
					.getComputedStyle(bv, null) || {
				marginRight : 0
			}).marginRight, 10) || 0) === 0
		}
		bx.innerHTML = "";
		bD.removeChild(bx);
		if (bB.attachEvent) {
			for (bE in {
				submit : 1,
				change : 1,
				focusin : 1
			}) {
				bw = "on" + bE;
				bK = (bw in bB);
				if (!bK) {
					bB.setAttribute(bw, "return;");
					bK = (typeof bB[bw] === "function")
				}
				by[bE + "Bubbles"] = bK
			}
		}
		bx = br = bC = bs = bz = bv = bB = bA = null;
		return by
	})();
	b.boxModel = b.support.boxModel;
	var aL = /^(?:\{.*\}|\[.*\])$/, av = /([a-z])([A-Z])/g;
	b
			.extend( {
				cache : {},
				uuid : 0,
				expando : "jQuery"
						+ (b.fn.jquery + Math.random()).replace(/\D/g, ""),
				noData : {
					embed : true,
					object : "clsid:D27CDB6E-AE6D-11cf-96B8-444553540000",
					applet : true
				},
				hasData : function(e) {
					e = e.nodeType ? b.cache[e[b.expando]] : e[b.expando];
					return !!e && !S(e)
				},
				data : function(bt, br, bv, bu) {
					if (!b.acceptData(bt)) {
						return
					}
					var bw, by, bz = b.expando, bx = typeof br === "string", bA = bt.nodeType, e = bA ? b.cache
							: bt, bs = bA ? bt[b.expando] : bt[b.expando]
							&& b.expando;
					if ((!bs || (bu && bs && (e[bs] && !e[bs][bz]))) && bx
							&& bv === K) {
						return
					}
					if (!bs) {
						if (bA) {
							bt[b.expando] = bs = ++b.uuid
						} else {
							bs = b.expando
						}
					}
					if (!e[bs]) {
						e[bs] = {};
						if (!bA) {
							e[bs].toJSON = b.noop
						}
					}
					if (typeof br === "object" || typeof br === "function") {
						if (bu) {
							e[bs][bz] = b.extend(e[bs][bz], br)
						} else {
							e[bs] = b.extend(e[bs], br)
						}
					}
					bw = e[bs];
					if (bu) {
						if (!bw[bz]) {
							bw[bz] = {}
						}
						bw = bw[bz]
					}
					if (bv !== K) {
						bw[b.camelCase(br)] = bv
					}
					if (br === "events" && !bw[br]) {
						return bw[bz] && bw[bz].events
					}
					if (bx) {
						by = bw[br];
						if (by == null) {
							by = bw[b.camelCase(br)]
						}
					} else {
						by = bw
					}
					return by
				},
				removeData : function(bu, bs, bv) {
					if (!b.acceptData(bu)) {
						return
					}
					var bw, bx = b.expando, by = bu.nodeType, br = by ? b.cache
							: bu, bt = by ? bu[b.expando] : b.expando;
					if (!br[bt]) {
						return
					}
					if (bs) {
						bw = bv ? br[bt][bx] : br[bt];
						if (bw) {
							if (!bw[bs]) {
								bs = b.camelCase(bs)
							}
							delete bw[bs];
							if (!S(bw)) {
								return
							}
						}
					}
					if (bv) {
						delete br[bt][bx];
						if (!S(br[bt])) {
							return
						}
					}
					var e = br[bt][bx];
					if (b.support.deleteExpando || !br.setInterval) {
						delete br[bt]
					} else {
						br[bt] = null
					}
					if (e) {
						br[bt] = {};
						if (!by) {
							br[bt].toJSON = b.noop
						}
						br[bt][bx] = e
					} else {
						if (by) {
							if (b.support.deleteExpando) {
								delete bu[b.expando]
							} else {
								if (bu.removeAttribute) {
									bu.removeAttribute(b.expando)
								} else {
									bu[b.expando] = null
								}
							}
						}
					}
				},
				_data : function(br, e, bs) {
					return b.data(br, e, bs, true)
				},
				acceptData : function(br) {
					if (br.nodeName) {
						var e = b.noData[br.nodeName.toLowerCase()];
						if (e) {
							return !(e === true || br.getAttribute("classid") !== e)
						}
					}
					return true
				}
			});
	b.fn.extend( {
		data : function(bu, bw) {
			var bv = null;
			if (typeof bu === "undefined") {
				if (this.length) {
					bv = b.data(this[0]);
					if (this[0].nodeType === 1) {
						var e = this[0].attributes, bs;
						for ( var bt = 0, br = e.length; bt < br; bt++) {
							bs = e[bt].name;
							if (bs.indexOf("data-") === 0) {
								bs = b.camelCase(bs.substring(5));
								a1(this[0], bs, bv[bs])
							}
						}
					}
				}
				return bv
			} else {
				if (typeof bu === "object") {
					return this.each(function() {
						b.data(this, bu)
					})
				}
			}
			var bx = bu.split(".");
			bx[1] = bx[1] ? "." + bx[1] : "";
			if (bw === K) {
				bv = this.triggerHandler("getData" + bx[1] + "!", [ bx[0] ]);
				if (bv === K && this.length) {
					bv = b.data(this[0], bu);
					bv = a1(this[0], bu, bv)
				}
				return bv === K && bx[1] ? this.data(bx[0]) : bv
			} else {
				return this.each(function() {
					var bz = b(this), by = [ bx[0], bw ];
					bz.triggerHandler("setData" + bx[1] + "!", by);
					b.data(this, bu, bw);
					bz.triggerHandler("changeData" + bx[1] + "!", by)
				})
			}
		},
		removeData : function(e) {
			return this.each(function() {
				b.removeData(this, e)
			})
		}
	});
	function a1(bt, bs, bu) {
		if (bu === K && bt.nodeType === 1) {
			var br = "data-" + bs.replace(av, "$1-$2").toLowerCase();
			bu = bt.getAttribute(br);
			if (typeof bu === "string") {
				try {
					bu = bu === "true" ? true : bu === "false" ? false
							: bu === "null" ? null
									: !b.isNaN(bu) ? parseFloat(bu) : aL
											.test(bu) ? b.parseJSON(bu) : bu
				} catch (bv) {
				}
				b.data(bt, bs, bu)
			} else {
				bu = K
			}
		}
		return bu
	}
	function S(br) {
		for ( var e in br) {
			if (e !== "toJSON") {
				return false
			}
		}
		return true
	}
	function bd(bu, bt, bw) {
		var bs = bt + "defer", br = bt + "queue", e = bt + "mark", bv = b.data(
				bu, bs, K, true);
		if (bv && (bw === "queue" || !b.data(bu, br, K, true))
				&& (bw === "mark" || !b.data(bu, e, K, true))) {
			setTimeout(function() {
				if (!b.data(bu, br, K, true) && !b.data(bu, e, K, true)) {
					b.removeData(bu, bs, true);
					bv.resolve()
				}
			}, 0)
		}
	}
	b.extend( {
		_mark : function(br, e) {
			if (br) {
				e = (e || "fx") + "mark";
				b.data(br, e, (b.data(br, e, K, true) || 0) + 1, true)
			}
		},
		_unmark : function(bu, bt, br) {
			if (bu !== true) {
				br = bt;
				bt = bu;
				bu = false
			}
			if (bt) {
				br = br || "fx";
				var e = br + "mark", bs = bu ? 0
						: ((b.data(bt, e, K, true) || 1) - 1);
				if (bs) {
					b.data(bt, e, bs, true)
				} else {
					b.removeData(bt, e, true);
					bd(bt, br, "mark")
				}
			}
		},
		queue : function(br, e, bt) {
			if (br) {
				e = (e || "fx") + "queue";
				var bs = b.data(br, e, K, true);
				if (bt) {
					if (!bs || b.isArray(bt)) {
						bs = b.data(br, e, b.makeArray(bt), true)
					} else {
						bs.push(bt)
					}
				}
				return bs || []
			}
		},
		dequeue : function(bt, bs) {
			bs = bs || "fx";
			var e = b.queue(bt, bs), br = e.shift(), bu;
			if (br === "inprogress") {
				br = e.shift()
			}
			if (br) {
				if (bs === "fx") {
					e.unshift("inprogress")
				}
				br.call(bt, function() {
					b.dequeue(bt, bs)
				})
			}
			if (!e.length) {
				b.removeData(bt, bs + "queue", true);
				bd(bt, bs, "queue")
			}
		}
	});
	b.fn.extend( {
		queue : function(e, br) {
			if (typeof e !== "string") {
				br = e;
				e = "fx"
			}
			if (br === K) {
				return b.queue(this[0], e)
			}
			return this.each(function() {
				var bs = b.queue(this, e, br);
				if (e === "fx" && bs[0] !== "inprogress") {
					b.dequeue(this, e)
				}
			})
		},
		dequeue : function(e) {
			return this.each(function() {
				b.dequeue(this, e)
			})
		},
		delay : function(br, e) {
			br = b.fx ? b.fx.speeds[br] || br : br;
			e = e || "fx";
			return this.queue(e, function() {
				var bs = this;
				setTimeout(function() {
					b.dequeue(bs, e)
				}, br)
			})
		},
		clearQueue : function(e) {
			return this.queue(e || "fx", [])
		},
		promise : function(bz, bs) {
			if (typeof bz !== "string") {
				bs = bz;
				bz = K
			}
			bz = bz || "fx";
			var e = b.Deferred(), br = this, bu = br.length, bx = 1, bv = bz
					+ "defer", bw = bz + "queue", by = bz + "mark", bt;
			function bA() {
				if (!(--bx)) {
					e.resolveWith(br, [ br ])
				}
			}
			while (bu--) {
				if ((bt = b.data(br[bu], bv, K, true)
						|| (b.data(br[bu], bw, K, true) || b.data(br[bu], by,
								K, true))
						&& b.data(br[bu], bv, b._Deferred(), true))) {
					bx++;
					bt.done(bA)
				}
			}
			bA();
			return e.promise()
		}
	});
	var aJ = /[\n\t\r]/g, ab = /\s+/, aN = /\r/g, g = /^(?:button|input)$/i, D = /^(?:button|input|object|select|textarea)$/i, l = /^a(?:rea)?$/i, aj = /^(?:autofocus|autoplay|async|checked|controls|defer|disabled|hidden|loop|multiple|open|readonly|required|scoped|selected)$/i, ba, aU;
	b.fn.extend( {
		attr : function(e, br) {
			return b.access(this, e, br, true, b.attr)
		},
		removeAttr : function(e) {
			return this.each(function() {
				b.removeAttr(this, e)
			})
		},
		prop : function(e, br) {
			return b.access(this, e, br, true, b.prop)
		},
		removeProp : function(e) {
			e = b.propFix[e] || e;
			return this.each(function() {
				try {
					this[e] = K;
					delete this[e]
				} catch (br) {
				}
			})
		},
		addClass : function(bu) {
			var bw, bs, br, bt, bv, bx, e;
			if (b.isFunction(bu)) {
				return this.each(function(by) {
					b(this).addClass(bu.call(this, by, this.className))
				})
			}
			if (bu && typeof bu === "string") {
				bw = bu.split(ab);
				for (bs = 0, br = this.length; bs < br; bs++) {
					bt = this[bs];
					if (bt.nodeType === 1) {
						if (!bt.className && bw.length === 1) {
							bt.className = bu
						} else {
							bv = " " + bt.className + " ";
							for (bx = 0, e = bw.length; bx < e; bx++) {
								if (!~bv.indexOf(" " + bw[bx] + " ")) {
									bv += bw[bx] + " "
								}
							}
							bt.className = b.trim(bv)
						}
					}
				}
			}
			return this
		},
		removeClass : function(bv) {
			var bw, bs, br, bu, bt, bx, e;
			if (b.isFunction(bv)) {
				return this.each(function(by) {
					b(this).removeClass(bv.call(this, by, this.className))
				})
			}
			if ((bv && typeof bv === "string") || bv === K) {
				bw = (bv || "").split(ab);
				for (bs = 0, br = this.length; bs < br; bs++) {
					bu = this[bs];
					if (bu.nodeType === 1 && bu.className) {
						if (bv) {
							bt = (" " + bu.className + " ").replace(aJ, " ");
							for (bx = 0, e = bw.length; bx < e; bx++) {
								bt = bt.replace(" " + bw[bx] + " ", " ")
							}
							bu.className = b.trim(bt)
						} else {
							bu.className = ""
						}
					}
				}
			}
			return this
		},
		toggleClass : function(bt, br) {
			var bs = typeof bt, e = typeof br === "boolean";
			if (b.isFunction(bt)) {
				return this.each(function(bu) {
					b(this).toggleClass(bt.call(this, bu, this.className, br),
							br)
				})
			}
			return this.each(function() {
				if (bs === "string") {
					var bw, bv = 0, bu = b(this), bx = br, by = bt.split(ab);
					while ((bw = by[bv++])) {
						bx = e ? bx : !bu.hasClass(bw);
						bu[bx ? "addClass" : "removeClass"](bw)
					}
				} else {
					if (bs === "undefined" || bs === "boolean") {
						if (this.className) {
							b._data(this, "__className__", this.className)
						}
						this.className = this.className || bt === false ? ""
								: b._data(this, "__className__") || ""
					}
				}
			})
		},
		hasClass : function(e) {
			var bt = " " + e + " ";
			for ( var bs = 0, br = this.length; bs < br; bs++) {
				if (this[bs].nodeType === 1
						&& (" " + this[bs].className + " ").replace(aJ, " ")
								.indexOf(bt) > -1) {
					return true
				}
			}
			return false
		},
		val : function(bt) {
			var e, br, bs = this[0];
			if (!arguments.length) {
				if (bs) {
					e = b.valHooks[bs.nodeName.toLowerCase()]
							|| b.valHooks[bs.type];
					if (e && "get" in e && (br = e.get(bs, "value")) !== K) {
						return br
					}
					br = bs.value;
					return typeof br === "string" ? br.replace(aN, "")
							: br == null ? "" : br
				}
				return K
			}
			var bu = b.isFunction(bt);
			return this.each(function(bw) {
				var bv = b(this), bx;
				if (this.nodeType !== 1) {
					return
				}
				if (bu) {
					bx = bt.call(this, bw, bv.val())
				} else {
					bx = bt
				}
				if (bx == null) {
					bx = ""
				} else {
					if (typeof bx === "number") {
						bx += ""
					} else {
						if (b.isArray(bx)) {
							bx = b.map(bx, function(by) {
								return by == null ? "" : by + ""
							})
						}
					}
				}
				e = b.valHooks[this.nodeName.toLowerCase()]
						|| b.valHooks[this.type];
				if (!e || !("set" in e) || e.set(this, bx, "value") === K) {
					this.value = bx
				}
			})
		}
	});
	b
			.extend( {
				valHooks : {
					option : {
						get : function(e) {
							var br = e.attributes.value;
							return !br || br.specified ? e.value : e.text
						}
					},
					select : {
						get : function(e) {
							var bw, bu = e.selectedIndex, bx = [], by = e.options, bt = e.type === "select-one";
							if (bu < 0) {
								return null
							}
							for ( var br = bt ? bu : 0, bv = bt ? bu + 1
									: by.length; br < bv; br++) {
								var bs = by[br];
								if (bs.selected
										&& (b.support.optDisabled ? !bs.disabled
												: bs.getAttribute("disabled") === null)
										&& (!bs.parentNode.disabled || !b
												.nodeName(bs.parentNode,
														"optgroup"))) {
									bw = b(bs).val();
									if (bt) {
										return bw
									}
									bx.push(bw)
								}
							}
							if (bt && !bx.length && by.length) {
								return b(by[bu]).val()
							}
							return bx
						},
						set : function(br, bs) {
							var e = b.makeArray(bs);
							b(br).find("option").each(
									function() {
										this.selected = b.inArray(
												b(this).val(), e) >= 0
									});
							if (!e.length) {
								br.selectedIndex = -1
							}
							return e
						}
					}
				},
				attrFn : {
					val : true,
					css : true,
					html : true,
					text : true,
					data : true,
					width : true,
					height : true,
					offset : true
				},
				attrFix : {
					tabindex : "tabIndex"
				},
				attr : function(bw, bt, bx, bv) {
					var br = bw.nodeType;
					if (!bw || br === 3 || br === 8 || br === 2) {
						return K
					}
					if (bv && bt in b.attrFn) {
						return b(bw)[bt](bx)
					}
					if (!("getAttribute" in bw)) {
						return b.prop(bw, bt, bx)
					}
					var bs, e, bu = br !== 1 || !b.isXMLDoc(bw);
					if (bu) {
						bt = b.attrFix[bt] || bt;
						e = b.attrHooks[bt];
						if (!e) {
							if (aj.test(bt)) {
								e = aU
							} else {
								if (ba) {
									e = ba
								}
							}
						}
					}
					if (bx !== K) {
						if (bx === null) {
							b.removeAttr(bw, bt);
							return K
						} else {
							if (e && "set" in e && bu
									&& (bs = e.set(bw, bx, bt)) !== K) {
								return bs
							} else {
								bw.setAttribute(bt, "" + bx);
								return bx
							}
						}
					} else {
						if (e && "get" in e && bu
								&& (bs = e.get(bw, bt)) !== null) {
							return bs
						} else {
							bs = bw.getAttribute(bt);
							return bs === null ? K : bs
						}
					}
				},
				removeAttr : function(br, e) {
					var bs;
					if (br.nodeType === 1) {
						e = b.attrFix[e] || e;
						b.attr(br, e, "");
						br.removeAttribute(e);
						if (aj.test(e) && (bs = b.propFix[e] || e) in br) {
							br[bs] = false
						}
					}
				},
				attrHooks : {
					type : {
						set : function(e, br) {
							if (g.test(e.nodeName) && e.parentNode) {
								b.error("type property can't be changed")
							} else {
								if (!b.support.radioValue && br === "radio"
										&& b.nodeName(e, "input")) {
									var bs = e.value;
									e.setAttribute("type", br);
									if (bs) {
										e.value = bs
									}
									return br
								}
							}
						}
					},
					value : {
						get : function(br, e) {
							if (ba && b.nodeName(br, "button")) {
								return ba.get(br, e)
							}
							return e in br ? br.value : null
						},
						set : function(br, bs, e) {
							if (ba && b.nodeName(br, "button")) {
								return ba.set(br, bs, e)
							}
							br.value = bs
						}
					}
				},
				propFix : {
					tabindex : "tabIndex",
					readonly : "readOnly",
					"for" : "htmlFor",
					"class" : "className",
					maxlength : "maxLength",
					cellspacing : "cellSpacing",
					cellpadding : "cellPadding",
					rowspan : "rowSpan",
					colspan : "colSpan",
					usemap : "useMap",
					frameborder : "frameBorder",
					contenteditable : "contentEditable"
				},
				prop : function(bv, bt, bw) {
					var br = bv.nodeType;
					if (!bv || br === 3 || br === 8 || br === 2) {
						return K
					}
					var bs, e, bu = br !== 1 || !b.isXMLDoc(bv);
					if (bu) {
						bt = b.propFix[bt] || bt;
						e = b.propHooks[bt]
					}
					if (bw !== K) {
						if (e && "set" in e && (bs = e.set(bv, bw, bt)) !== K) {
							return bs
						} else {
							return (bv[bt] = bw)
						}
					} else {
						if (e && "get" in e && (bs = e.get(bv, bt)) !== null) {
							return bs
						} else {
							return bv[bt]
						}
					}
				},
				propHooks : {
					tabIndex : {
						get : function(br) {
							var e = br.getAttributeNode("tabindex");
							return e && e.specified ? parseInt(e.value, 10) : D
									.test(br.nodeName)
									|| l.test(br.nodeName) && br.href ? 0 : K
						}
					}
				}
			});
	b.attrHooks.tabIndex = b.propHooks.tabIndex;
	aU = {
		get : function(br, e) {
			var bs;
			return b.prop(br, e) === true || (bs = br.getAttributeNode(e))
					&& bs.nodeValue !== false ? e.toLowerCase() : K
		},
		set : function(br, bt, e) {
			var bs;
			if (bt === false) {
				b.removeAttr(br, e)
			} else {
				bs = b.propFix[e] || e;
				if (bs in br) {
					br[bs] = true
				}
				br.setAttribute(e, e.toLowerCase())
			}
			return e
		}
	};
	if (!b.support.getSetAttribute) {
		ba = b.valHooks.button = {
			get : function(bs, br) {
				var e;
				e = bs.getAttributeNode(br);
				return e && e.nodeValue !== "" ? e.nodeValue : K
			},
			set : function(bs, bt, br) {
				var e = bs.getAttributeNode(br);
				if (!e) {
					e = ap.createAttribute(br);
					bs.setAttributeNode(e)
				}
				return (e.nodeValue = bt + "")
			}
		};
		b.each( [ "width", "height" ], function(br, e) {
			b.attrHooks[e] = b.extend(b.attrHooks[e], {
				set : function(bs, bt) {
					if (bt === "") {
						bs.setAttribute(e, "auto");
						return bt
					}
				}
			})
		})
	}
	if (!b.support.hrefNormalized) {
		b.each( [ "href", "src", "width", "height" ], function(br, e) {
			b.attrHooks[e] = b.extend(b.attrHooks[e], {
				get : function(bt) {
					var bs = bt.getAttribute(e, 2);
					return bs === null ? K : bs
				}
			})
		})
	}
	if (!b.support.style) {
		b.attrHooks.style = {
			get : function(e) {
				return e.style.cssText.toLowerCase() || K
			},
			set : function(e, br) {
				return (e.style.cssText = "" + br)
			}
		}
	}
	if (!b.support.optSelected) {
		b.propHooks.selected = b.extend(b.propHooks.selected, {
			get : function(br) {
				var e = br.parentNode;
				if (e) {
					e.selectedIndex;
					if (e.parentNode) {
						e.parentNode.selectedIndex
					}
				}
				return null
			}
		})
	}
	if (!b.support.checkOn) {
		b.each( [ "radio", "checkbox" ], function() {
			b.valHooks[this] = {
				get : function(e) {
					return e.getAttribute("value") === null ? "on" : e.value
				}
			}
		})
	}
	b.each( [ "radio", "checkbox" ], function() {
		b.valHooks[this] = b.extend(b.valHooks[this], {
			set : function(e, br) {
				if (b.isArray(br)) {
					return (e.checked = b.inArray(b(e).val(), br) >= 0)
				}
			}
		})
	});
	var aW = /\.(.*)$/, a9 = /^(?:textarea|input|select)$/i, N = /\./g, be = / /g, aB = /[^\w\s.|`]/g, G = function(
			e) {
		return e.replace(aB, "\\$&")
	};
	b.event = {
		add : function(bt, bx, bC, bv) {
			if (bt.nodeType === 3 || bt.nodeType === 8) {
				return
			}
			if (bC === false) {
				bC = bg
			} else {
				if (!bC) {
					return
				}
			}
			var br, bB;
			if (bC.handler) {
				br = bC;
				bC = br.handler
			}
			if (!bC.guid) {
				bC.guid = b.guid++
			}
			var by = b._data(bt);
			if (!by) {
				return
			}
			var bD = by.events, bw = by.handle;
			if (!bD) {
				by.events = bD = {}
			}
			if (!bw) {
				by.handle = bw = function(bE) {
					return typeof b !== "undefined"
							&& (!bE || b.event.triggered !== bE.type) ? b.event.handle
							.apply(bw.elem, arguments)
							: K
				}
			}
			bw.elem = bt;
			bx = bx.split(" ");
			var bA, bu = 0, e;
			while ((bA = bx[bu++])) {
				bB = br ? b.extend( {}, br) : {
					handler : bC,
					data : bv
				};
				if (bA.indexOf(".") > -1) {
					e = bA.split(".");
					bA = e.shift();
					bB.namespace = e.slice(0).sort().join(".")
				} else {
					e = [];
					bB.namespace = ""
				}
				bB.type = bA;
				if (!bB.guid) {
					bB.guid = bC.guid
				}
				var bs = bD[bA], bz = b.event.special[bA] || {};
				if (!bs) {
					bs = bD[bA] = [];
					if (!bz.setup || bz.setup.call(bt, bv, e, bw) === false) {
						if (bt.addEventListener) {
							bt.addEventListener(bA, bw, false)
						} else {
							if (bt.attachEvent) {
								bt.attachEvent("on" + bA, bw)
							}
						}
					}
				}
				if (bz.add) {
					bz.add.call(bt, bB);
					if (!bB.handler.guid) {
						bB.handler.guid = bC.guid
					}
				}
				bs.push(bB);
				b.event.global[bA] = true
			}
			bt = null
		},
		global : {},
		remove : function(bF, bA, bs, bw) {
			if (bF.nodeType === 3 || bF.nodeType === 8) {
				return
			}
			if (bs === false) {
				bs = bg
			}
			var bI, bv, bx, bC, bD = 0, bt, by, bB, bu, bz, e, bH, bE = b
					.hasData(bF)
					&& b._data(bF), br = bE && bE.events;
			if (!bE || !br) {
				return
			}
			if (bA && bA.type) {
				bs = bA.handler;
				bA = bA.type
			}
			if (!bA || typeof bA === "string" && bA.charAt(0) === ".") {
				bA = bA || "";
				for (bv in br) {
					b.event.remove(bF, bv + bA)
				}
				return
			}
			bA = bA.split(" ");
			while ((bv = bA[bD++])) {
				bH = bv;
				e = null;
				bt = bv.indexOf(".") < 0;
				by = [];
				if (!bt) {
					by = bv.split(".");
					bv = by.shift();
					bB = new RegExp("(^|\\.)"
							+ b.map(by.slice(0).sort(), G)
									.join("\\.(?:.*\\.)?") + "(\\.|$)")
				}
				bz = br[bv];
				if (!bz) {
					continue
				}
				if (!bs) {
					for (bC = 0; bC < bz.length; bC++) {
						e = bz[bC];
						if (bt || bB.test(e.namespace)) {
							b.event.remove(bF, bH, e.handler, bC);
							bz.splice(bC--, 1)
						}
					}
					continue
				}
				bu = b.event.special[bv] || {};
				for (bC = bw || 0; bC < bz.length; bC++) {
					e = bz[bC];
					if (bs.guid === e.guid) {
						if (bt || bB.test(e.namespace)) {
							if (bw == null) {
								bz.splice(bC--, 1)
							}
							if (bu.remove) {
								bu.remove.call(bF, e)
							}
						}
						if (bw != null) {
							break
						}
					}
				}
				if (bz.length === 0 || bw != null && bz.length === 1) {
					if (!bu.teardown || bu.teardown.call(bF, by) === false) {
						b.removeEvent(bF, bv, bE.handle)
					}
					bI = null;
					delete br[bv]
				}
			}
			if (b.isEmptyObject(br)) {
				var bG = bE.handle;
				if (bG) {
					bG.elem = null
				}
				delete bE.events;
				delete bE.handle;
				if (b.isEmptyObject(bE)) {
					b.removeData(bF, K, true)
				}
			}
		},
		customEvent : {
			getData : true,
			setData : true,
			changeData : true
		},
		trigger : function(e, bx, bv, bC) {
			var bA = e.type || e, bs = [], br;
			if (bA.indexOf("!") >= 0) {
				bA = bA.slice(0, -1);
				br = true
			}
			if (bA.indexOf(".") >= 0) {
				bs = bA.split(".");
				bA = bs.shift();
				bs.sort()
			}
			if ((!bv || b.event.customEvent[bA]) && !b.event.global[bA]) {
				return
			}
			e = typeof e === "object" ? e[b.expando] ? e : new b.Event(bA, e)
					: new b.Event(bA);
			e.type = bA;
			e.exclusive = br;
			e.namespace = bs.join(".");
			e.namespace_re = new RegExp("(^|\\.)" + bs.join("\\.(?:.*\\.)?")
					+ "(\\.|$)");
			if (bC || !bv) {
				e.preventDefault();
				e.stopPropagation()
			}
			if (!bv) {
				b.each(b.cache, function() {
					var bE = b.expando, bD = this[bE];
					if (bD && bD.events && bD.events[bA]) {
						b.event.trigger(e, bx, bD.handle.elem)
					}
				});
				return
			}
			if (bv.nodeType === 3 || bv.nodeType === 8) {
				return
			}
			e.result = K;
			e.target = bv;
			bx = bx != null ? b.makeArray(bx) : [];
			bx.unshift(e);
			var bB = bv, bt = bA.indexOf(":") < 0 ? "on" + bA : "";
			do {
				var by = b._data(bB, "handle");
				e.currentTarget = bB;
				if (by) {
					by.apply(bB, bx)
				}
				if (bt && b.acceptData(bB) && bB[bt]
						&& bB[bt].apply(bB, bx) === false) {
					e.result = false;
					e.preventDefault()
				}
				bB = bB.parentNode || bB.ownerDocument
						|| bB === e.target.ownerDocument && a7
			} while (bB && !e.isPropagationStopped());
			if (!e.isDefaultPrevented()) {
				var bu, bz = b.event.special[bA] || {};
				if ((!bz._default || bz._default.call(bv.ownerDocument, e) === false)
						&& !(bA === "click" && b.nodeName(bv, "a"))
						&& b.acceptData(bv)) {
					try {
						if (bt && bv[bA]) {
							bu = bv[bt];
							if (bu) {
								bv[bt] = null
							}
							b.event.triggered = bA;
							bv[bA]()
						}
					} catch (bw) {
					}
					if (bu) {
						bv[bt] = bu
					}
					b.event.triggered = K
				}
			}
			return e.result
		},
		handle : function(bx) {
			bx = b.event.fix(bx || a7.event);
			var br = ((b._data(this, "events") || {})[bx.type] || []).slice(0), bw = !bx.exclusive
					&& !bx.namespace, bu = Array.prototype.slice.call(
					arguments, 0);
			bu[0] = bx;
			bx.currentTarget = this;
			for ( var bt = 0, e = br.length; bt < e; bt++) {
				var bv = br[bt];
				if (bw || bx.namespace_re.test(bv.namespace)) {
					bx.handler = bv.handler;
					bx.data = bv.data;
					bx.handleObj = bv;
					var bs = bv.handler.apply(this, bu);
					if (bs !== K) {
						bx.result = bs;
						if (bs === false) {
							bx.preventDefault();
							bx.stopPropagation()
						}
					}
					if (bx.isImmediatePropagationStopped()) {
						break
					}
				}
			}
			return bx.result
		},
		props : "altKey attrChange attrName bubbles button cancelable charCode clientX clientY ctrlKey currentTarget data detail eventPhase fromElement handler keyCode layerX layerY metaKey newValue offsetX offsetY pageX pageY prevValue relatedNode relatedTarget screenX screenY shiftKey srcElement target toElement view wheelDelta which"
				.split(" "),
		fix : function(bu) {
			if (bu[b.expando]) {
				return bu
			}
			var br = bu;
			bu = b.Event(br);
			for ( var bs = this.props.length, bw; bs;) {
				bw = this.props[--bs];
				bu[bw] = br[bw]
			}
			if (!bu.target) {
				bu.target = bu.srcElement || ap
			}
			if (bu.target.nodeType === 3) {
				bu.target = bu.target.parentNode
			}
			if (!bu.relatedTarget && bu.fromElement) {
				bu.relatedTarget = bu.fromElement === bu.target ? bu.toElement
						: bu.fromElement
			}
			if (bu.pageX == null && bu.clientX != null) {
				var bt = bu.target.ownerDocument || ap, bv = bt.documentElement, e = bt.body;
				bu.pageX = bu.clientX
						+ (bv && bv.scrollLeft || e && e.scrollLeft || 0)
						- (bv && bv.clientLeft || e && e.clientLeft || 0);
				bu.pageY = bu.clientY
						+ (bv && bv.scrollTop || e && e.scrollTop || 0)
						- (bv && bv.clientTop || e && e.clientTop || 0)
			}
			if (bu.which == null && (bu.charCode != null || bu.keyCode != null)) {
				bu.which = bu.charCode != null ? bu.charCode : bu.keyCode
			}
			if (!bu.metaKey && bu.ctrlKey) {
				bu.metaKey = bu.ctrlKey
			}
			if (!bu.which && bu.button !== K) {
				bu.which = (bu.button & 1 ? 1 : (bu.button & 2 ? 3
						: (bu.button & 4 ? 2 : 0)))
			}
			return bu
		},
		guid : 100000000,
		proxy : b.proxy,
		special : {
			ready : {
				setup : b.bindReady,
				teardown : b.noop
			},
			live : {
				add : function(e) {
					b.event.add(this, p(e.origType, e.selector), b.extend( {},
							e, {
								handler : ag,
								guid : e.handler.guid
							}))
				},
				remove : function(e) {
					b.event.remove(this, p(e.origType, e.selector), e)
				}
			},
			beforeunload : {
				setup : function(bs, br, e) {
					if (b.isWindow(this)) {
						this.onbeforeunload = e
					}
				},
				teardown : function(br, e) {
					if (this.onbeforeunload === e) {
						this.onbeforeunload = null
					}
				}
			}
		}
	};
	b.removeEvent = ap.removeEventListener ? function(br, e, bs) {
		if (br.removeEventListener) {
			br.removeEventListener(e, bs, false)
		}
	} : function(br, e, bs) {
		if (br.detachEvent) {
			br.detachEvent("on" + e, bs)
		}
	};
	b.Event = function(br, e) {
		if (!this.preventDefault) {
			return new b.Event(br, e)
		}
		if (br && br.type) {
			this.originalEvent = br;
			this.type = br.type;
			this.isDefaultPrevented = (br.defaultPrevented
					|| br.returnValue === false || br.getPreventDefault
					&& br.getPreventDefault()) ? i : bg
		} else {
			this.type = br
		}
		if (e) {
			b.extend(this, e)
		}
		this.timeStamp = b.now();
		this[b.expando] = true
	};
	function bg() {
		return false
	}
	function i() {
		return true
	}
	b.Event.prototype = {
		preventDefault : function() {
			this.isDefaultPrevented = i;
			var br = this.originalEvent;
			if (!br) {
				return
			}
			if (br.preventDefault) {
				br.preventDefault()
			} else {
				br.returnValue = false
			}
		},
		stopPropagation : function() {
			this.isPropagationStopped = i;
			var br = this.originalEvent;
			if (!br) {
				return
			}
			if (br.stopPropagation) {
				br.stopPropagation()
			}
			br.cancelBubble = true
		},
		stopImmediatePropagation : function() {
			this.isImmediatePropagationStopped = i;
			this.stopPropagation()
		},
		isDefaultPrevented : bg,
		isPropagationStopped : bg,
		isImmediatePropagationStopped : bg
	};
	var aa = function(bs) {
		var bt = bs.relatedTarget, e = false, br = bs.type;
		bs.type = bs.data;
		if (bt !== this) {
			if (bt) {
				e = b.contains(this, bt)
			}
			if (!e) {
				b.event.handle.apply(this, arguments);
				bs.type = br
			}
		}
	}, aR = function(e) {
		e.type = e.data;
		b.event.handle.apply(this, arguments)
	};
	b.each( {
		mouseenter : "mouseover",
		mouseleave : "mouseout"
	}, function(br, e) {
		b.event.special[br] = {
			setup : function(bs) {
				b.event.add(this, e, bs && bs.selector ? aR : aa, br)
			},
			teardown : function(bs) {
				b.event.remove(this, e, bs && bs.selector ? aR : aa)
			}
		}
	});
	if (!b.support.submitBubbles) {
		b.event.special.submit = {
			setup : function(br, e) {
				if (!b.nodeName(this, "form")) {
					b.event.add(this, "click.specialSubmit",
							function(bu) {
								var bt = bu.target, bs = b
										.nodeName(bt, "input") ? bt.type : "";
								if ((bs === "submit" || bs === "image")
										&& b(bt).closest("form").length) {
									aT("submit", this, arguments)
								}
							});
					b.event.add(this, "keypress.specialSubmit",
							function(bu) {
								var bt = bu.target, bs = b
										.nodeName(bt, "input") ? bt.type : "";
								if ((bs === "text" || bs === "password")
										&& b(bt).closest("form").length
										&& bu.keyCode === 13) {
									aT("submit", this, arguments)
								}
							})
				} else {
					return false
				}
			},
			teardown : function(e) {
				b.event.remove(this, ".specialSubmit")
			}
		}
	}
	if (!b.support.changeBubbles) {
		var bj, k = function(br) {
			var e = b.nodeName(br, "input") ? br.type : "", bs = br.value;
			if (e === "radio" || e === "checkbox") {
				bs = br.checked
			} else {
				if (e === "select-multiple") {
					bs = br.selectedIndex > -1 ? b.map(br.options,
							function(bt) {
								return bt.selected
							}).join("-") : ""
				} else {
					if (b.nodeName(br, "select")) {
						bs = br.selectedIndex
					}
				}
			}
			return bs
		}, Y = function Y(bt) {
			var br = bt.target, bs, bu;
			if (!a9.test(br.nodeName) || br.readOnly) {
				return
			}
			bs = b._data(br, "_change_data");
			bu = k(br);
			if (bt.type !== "focusout" || br.type !== "radio") {
				b._data(br, "_change_data", bu)
			}
			if (bs === K || bu === bs) {
				return
			}
			if (bs != null || bu) {
				bt.type = "change";
				bt.liveFired = K;
				b.event.trigger(bt, arguments[1], br)
			}
		};
		b.event.special.change = {
			filters : {
				focusout : Y,
				beforedeactivate : Y,
				click : function(bt) {
					var bs = bt.target, br = b.nodeName(bs, "input") ? bs.type
							: "";
					if (br === "radio" || br === "checkbox"
							|| b.nodeName(bs, "select")) {
						Y.call(this, bt)
					}
				},
				keydown : function(bt) {
					var bs = bt.target, br = b.nodeName(bs, "input") ? bs.type
							: "";
					if ((bt.keyCode === 13 && !b.nodeName(bs, "textarea"))
							|| (bt.keyCode === 32 && (br === "checkbox" || br === "radio"))
							|| br === "select-multiple") {
						Y.call(this, bt)
					}
				},
				beforeactivate : function(bs) {
					var br = bs.target;
					b._data(br, "_change_data", k(br))
				}
			},
			setup : function(bs, br) {
				if (this.type === "file") {
					return false
				}
				for ( var e in bj) {
					b.event.add(this, e + ".specialChange", bj[e])
				}
				return a9.test(this.nodeName)
			},
			teardown : function(e) {
				b.event.remove(this, ".specialChange");
				return a9.test(this.nodeName)
			}
		};
		bj = b.event.special.change.filters;
		bj.focus = bj.beforeactivate
	}
	function aT(br, bt, e) {
		var bs = b.extend( {}, e[0]);
		bs.type = br;
		bs.originalEvent = {};
		bs.liveFired = K;
		b.event.handle.call(bt, bs);
		if (bs.isDefaultPrevented()) {
			e[0].preventDefault()
		}
	}
	if (!b.support.focusinBubbles) {
		b.each( {
			focus : "focusin",
			blur : "focusout"
		}, function(bt, e) {
			var br = 0;
			b.event.special[e] = {
				setup : function() {
					if (br++ === 0) {
						ap.addEventListener(bt, bs, true)
					}
				},
				teardown : function() {
					if (--br === 0) {
						ap.removeEventListener(bt, bs, true)
					}
				}
			};
			function bs(bu) {
				var bv = b.event.fix(bu);
				bv.type = e;
				bv.originalEvent = {};
				b.event.trigger(bv, null, bv.target);
				if (bv.isDefaultPrevented()) {
					bu.preventDefault()
				}
			}
		})
	}
	b.each( [ "bind", "one" ], function(br, e) {
		b.fn[e] = function(bx, by, bw) {
			var bv;
			if (typeof bx === "object") {
				for ( var bu in bx) {
					this[e](bu, by, bx[bu], bw)
				}
				return this
			}
			if (arguments.length === 2 || by === false) {
				bw = by;
				by = K
			}
			if (e === "one") {
				bv = function(bz) {
					b(this).unbind(bz, bv);
					return bw.apply(this, arguments)
				};
				bv.guid = bw.guid || b.guid++
			} else {
				bv = bw
			}
			if (bx === "unload" && e !== "one") {
				this.one(bx, by, bw)
			} else {
				for ( var bt = 0, bs = this.length; bt < bs; bt++) {
					b.event.add(this[bt], bx, bv, by)
				}
			}
			return this
		}
	});
	b.fn.extend( {
		unbind : function(bu, bt) {
			if (typeof bu === "object" && !bu.preventDefault) {
				for ( var bs in bu) {
					this.unbind(bs, bu[bs])
				}
			} else {
				for ( var br = 0, e = this.length; br < e; br++) {
					b.event.remove(this[br], bu, bt)
				}
			}
			return this
		},
		delegate : function(e, br, bt, bs) {
			return this.live(br, bt, bs, e)
		},
		undelegate : function(e, br, bs) {
			if (arguments.length === 0) {
				return this.unbind("live")
			} else {
				return this.die(br, null, bs, e)
			}
		},
		trigger : function(e, br) {
			return this.each(function() {
				b.event.trigger(e, br, this)
			})
		},
		triggerHandler : function(e, br) {
			if (this[0]) {
				return b.event.trigger(e, br, this[0], true)
			}
		},
		toggle : function(bt) {
			var br = arguments, e = bt.guid || b.guid++, bs = 0, bu = function(
					bv) {
				var bw = (b.data(this, "lastToggle" + bt.guid) || 0) % bs;
				b.data(this, "lastToggle" + bt.guid, bw + 1);
				bv.preventDefault();
				return br[bw].apply(this, arguments) || false
			};
			bu.guid = e;
			while (bs < br.length) {
				br[bs++].guid = e
			}
			return this.click(bu)
		},
		hover : function(e, br) {
			return this.mouseenter(e).mouseleave(br || e)
		}
	});
	var aP = {
		focus : "focusin",
		blur : "focusout",
		mouseenter : "mouseover",
		mouseleave : "mouseout"
	};
	b
			.each(
					[ "live", "die" ],
					function(br, e) {
						b.fn[e] = function(bB, by, bD, bu) {
							var bC, bz = 0, bA, bt, bF, bw = bu
									|| this.selector, bs = bu ? this
									: b(this.context);
							if (typeof bB === "object" && !bB.preventDefault) {
								for ( var bE in bB) {
									bs[e](bE, by, bB[bE], bw)
								}
								return this
							}
							if (e === "die" && !bB && bu
									&& bu.charAt(0) === ".") {
								bs.unbind(bu);
								return this
							}
							if (by === false || b.isFunction(by)) {
								bD = by || bg;
								by = K
							}
							bB = (bB || "").split(" ");
							while ((bC = bB[bz++]) != null) {
								bA = aW.exec(bC);
								bt = "";
								if (bA) {
									bt = bA[0];
									bC = bC.replace(aW, "")
								}
								if (bC === "hover") {
									bB.push("mouseenter" + bt, "mouseleave"
											+ bt);
									continue
								}
								bF = bC;
								if (aP[bC]) {
									bB.push(aP[bC] + bt);
									bC = bC + bt
								} else {
									bC = (aP[bC] || bC) + bt
								}
								if (e === "live") {
									for ( var bx = 0, bv = bs.length; bx < bv; bx++) {
										b.event.add(bs[bx],
												"live." + p(bC, bw), {
													data : by,
													selector : bw,
													handler : bD,
													origType : bC,
													origHandler : bD,
													preType : bF
												})
									}
								} else {
									bs.unbind("live." + p(bC, bw), bD)
								}
							}
							return this
						}
					});
	function ag(bB) {
		var by, bt, bH, bv, e, bD, bA, bC, bz, bG, bx, bw, bF, bE = [], bu = [], br = b
				._data(this, "events");
		if (bB.liveFired === this || !br || !br.live || bB.target.disabled
				|| bB.button && bB.type === "click") {
			return
		}
		if (bB.namespace) {
			bw = new RegExp("(^|\\.)"
					+ bB.namespace.split(".").join("\\.(?:.*\\.)?") + "(\\.|$)")
		}
		bB.liveFired = this;
		var bs = br.live.slice(0);
		for (bA = 0; bA < bs.length; bA++) {
			e = bs[bA];
			if (e.origType.replace(aW, "") === bB.type) {
				bu.push(e.selector)
			} else {
				bs.splice(bA--, 1)
			}
		}
		bv = b(bB.target).closest(bu, bB.currentTarget);
		for (bC = 0, bz = bv.length; bC < bz; bC++) {
			bx = bv[bC];
			for (bA = 0; bA < bs.length; bA++) {
				e = bs[bA];
				if (bx.selector === e.selector && (!bw || bw.test(e.namespace))
						&& !bx.elem.disabled) {
					bD = bx.elem;
					bH = null;
					if (e.preType === "mouseenter"
							|| e.preType === "mouseleave") {
						bB.type = e.preType;
						bH = b(bB.relatedTarget).closest(e.selector)[0];
						if (bH && b.contains(bD, bH)) {
							bH = bD
						}
					}
					if (!bH || bH !== bD) {
						bE.push( {
							elem : bD,
							handleObj : e,
							level : bx.level
						})
					}
				}
			}
		}
		for (bC = 0, bz = bE.length; bC < bz; bC++) {
			bv = bE[bC];
			if (bt && bv.level > bt) {
				break
			}
			bB.currentTarget = bv.elem;
			bB.data = bv.handleObj.data;
			bB.handleObj = bv.handleObj;
			bF = bv.handleObj.origHandler.apply(bv.elem, arguments);
			if (bF === false || bB.isPropagationStopped()) {
				bt = bv.level;
				if (bF === false) {
					by = false
				}
				if (bB.isImmediatePropagationStopped()) {
					break
				}
			}
		}
		return by
	}
	function p(br, e) {
		return (br && br !== "*" ? br + "." : "")
				+ e.replace(N, "`").replace(be, "&")
	}
	b
			.each(
					("blur focus focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select submit keydown keypress keyup error")
							.split(" "), function(br, e) {
						b.fn[e] = function(bt, bs) {
							if (bs == null) {
								bs = bt;
								bt = null
							}
							return arguments.length > 0 ? this.bind(e, bt, bs)
									: this.trigger(e)
						};
						if (b.attrFn) {
							b.attrFn[e] = true
						}
					});
	/*
	 * Sizzle CSS Selector Engine Copyright 2011, The Dojo Foundation Released
	 * under the MIT, BSD, and GPL Licenses. More information:
	 * http://sizzlejs.com/
	 */
	(function() {
		var bB = /((?:\((?:\([^()]+\)|[^()]+)+\)|\[(?:\[[^\[\]]*\]|['"][^'"]*['"]|[^\[\]'"]+)+\]|\\.|[^ >+~,(\[\\]+)+|[>+~])(\s*,\s*)?((?:.|\r|\n)*)/g, bC = 0, bF = Object.prototype.toString, bw = false, bv = true, bD = /\\/g, bJ = /\W/;
		[ 0, 0 ].sort(function() {
			bv = false;
			return 0
		});
		var bt = function(bO, e, bR, bS) {
			bR = bR || [];
			e = e || ap;
			var bU = e;
			if (e.nodeType !== 1 && e.nodeType !== 9) {
				return []
			}
			if (!bO || typeof bO !== "string") {
				return bR
			}
			var bL, bW, bZ, bK, bV, bY, bX, bQ, bN = true, bM = bt.isXML(e), bP = [], bT = bO;
			do {
				bB.exec("");
				bL = bB.exec(bT);
				if (bL) {
					bT = bL[3];
					bP.push(bL[1]);
					if (bL[2]) {
						bK = bL[3];
						break
					}
				}
			} while (bL);
			if (bP.length > 1 && bx.exec(bO)) {
				if (bP.length === 2 && by.relative[bP[0]]) {
					bW = bG(bP[0] + bP[1], e)
				} else {
					bW = by.relative[bP[0]] ? [ e ] : bt(bP.shift(), e);
					while (bP.length) {
						bO = bP.shift();
						if (by.relative[bO]) {
							bO += bP.shift()
						}
						bW = bG(bO, bW)
					}
				}
			} else {
				if (!bS && bP.length > 1 && e.nodeType === 9 && !bM
						&& by.match.ID.test(bP[0])
						&& !by.match.ID.test(bP[bP.length - 1])) {
					bV = bt.find(bP.shift(), e, bM);
					e = bV.expr ? bt.filter(bV.expr, bV.set)[0] : bV.set[0]
				}
				if (e) {
					bV = bS ? {
						expr : bP.pop(),
						set : bz(bS)
					} : bt.find(bP.pop(),
							bP.length === 1 && (bP[0] === "~" || bP[0] === "+")
									&& e.parentNode ? e.parentNode : e, bM);
					bW = bV.expr ? bt.filter(bV.expr, bV.set) : bV.set;
					if (bP.length > 0) {
						bZ = bz(bW)
					} else {
						bN = false
					}
					while (bP.length) {
						bY = bP.pop();
						bX = bY;
						if (!by.relative[bY]) {
							bY = ""
						} else {
							bX = bP.pop()
						}
						if (bX == null) {
							bX = e
						}
						by.relative[bY](bZ, bX, bM)
					}
				} else {
					bZ = bP = []
				}
			}
			if (!bZ) {
				bZ = bW
			}
			if (!bZ) {
				bt.error(bY || bO)
			}
			if (bF.call(bZ) === "[object Array]") {
				if (!bN) {
					bR.push.apply(bR, bZ)
				} else {
					if (e && e.nodeType === 1) {
						for (bQ = 0; bZ[bQ] != null; bQ++) {
							if (bZ[bQ]
									&& (bZ[bQ] === true || bZ[bQ].nodeType === 1
											&& bt.contains(e, bZ[bQ]))) {
								bR.push(bW[bQ])
							}
						}
					} else {
						for (bQ = 0; bZ[bQ] != null; bQ++) {
							if (bZ[bQ] && bZ[bQ].nodeType === 1) {
								bR.push(bW[bQ])
							}
						}
					}
				}
			} else {
				bz(bZ, bR)
			}
			if (bK) {
				bt(bK, bU, bR, bS);
				bt.uniqueSort(bR)
			}
			return bR
		};
		bt.uniqueSort = function(bK) {
			if (bE) {
				bw = bv;
				bK.sort(bE);
				if (bw) {
					for ( var e = 1; e < bK.length; e++) {
						if (bK[e] === bK[e - 1]) {
							bK.splice(e--, 1)
						}
					}
				}
			}
			return bK
		};
		bt.matches = function(e, bK) {
			return bt(e, null, null, bK)
		};
		bt.matchesSelector = function(e, bK) {
			return bt(bK, null, null, [ e ]).length > 0
		};
		bt.find = function(bQ, e, bR) {
			var bP;
			if (!bQ) {
				return []
			}
			for ( var bM = 0, bL = by.order.length; bM < bL; bM++) {
				var bN, bO = by.order[bM];
				if ((bN = by.leftMatch[bO].exec(bQ))) {
					var bK = bN[1];
					bN.splice(1, 1);
					if (bK.substr(bK.length - 1) !== "\\") {
						bN[1] = (bN[1] || "").replace(bD, "");
						bP = by.find[bO](bN, e, bR);
						if (bP != null) {
							bQ = bQ.replace(by.match[bO], "");
							break
						}
					}
				}
			}
			if (!bP) {
				bP = typeof e.getElementsByTagName !== "undefined" ? e
						.getElementsByTagName("*") : []
			}
			return {
				set : bP,
				expr : bQ
			}
		};
		bt.filter = function(bU, bT, bX, bN) {
			var bP, e, bL = bU, bZ = [], bR = bT, bQ = bT && bT[0]
					&& bt.isXML(bT[0]);
			while (bU && bT.length) {
				for ( var bS in by.filter) {
					if ((bP = by.leftMatch[bS].exec(bU)) != null && bP[2]) {
						var bY, bW, bK = by.filter[bS], bM = bP[1];
						e = false;
						bP.splice(1, 1);
						if (bM.substr(bM.length - 1) === "\\") {
							continue
						}
						if (bR === bZ) {
							bZ = []
						}
						if (by.preFilter[bS]) {
							bP = by.preFilter[bS](bP, bR, bX, bZ, bN, bQ);
							if (!bP) {
								e = bY = true
							} else {
								if (bP === true) {
									continue
								}
							}
						}
						if (bP) {
							for ( var bO = 0; (bW = bR[bO]) != null; bO++) {
								if (bW) {
									bY = bK(bW, bP, bO, bR);
									var bV = bN ^ !!bY;
									if (bX && bY != null) {
										if (bV) {
											e = true
										} else {
											bR[bO] = false
										}
									} else {
										if (bV) {
											bZ.push(bW);
											e = true
										}
									}
								}
							}
						}
						if (bY !== K) {
							if (!bX) {
								bR = bZ
							}
							bU = bU.replace(by.match[bS], "");
							if (!e) {
								return []
							}
							break
						}
					}
				}
				if (bU === bL) {
					if (e == null) {
						bt.error(bU)
					} else {
						break
					}
				}
				bL = bU
			}
			return bR
		};
		bt.error = function(e) {
			throw "Syntax error, unrecognized expression: " + e
		};
		var by = bt.selectors = {
			order : [ "ID", "NAME", "TAG" ],
			match : {
				ID : /#((?:[\w\u00c0-\uFFFF\-]|\\.)+)/,
				CLASS : /\.((?:[\w\u00c0-\uFFFF\-]|\\.)+)/,
				NAME : /\[name=['"]*((?:[\w\u00c0-\uFFFF\-]|\\.)+)['"]*\]/,
				ATTR : /\[\s*((?:[\w\u00c0-\uFFFF\-]|\\.)+)\s*(?:(\S?=)\s*(?:(['"])(.*?)\3|(#?(?:[\w\u00c0-\uFFFF\-]|\\.)*)|)|)\s*\]/,
				TAG : /^((?:[\w\u00c0-\uFFFF\*\-]|\\.)+)/,
				CHILD : /:(only|nth|last|first)-child(?:\(\s*(even|odd|(?:[+\-]?\d+|(?:[+\-]?\d*)?n\s*(?:[+\-]\s*\d+)?))\s*\))?/,
				POS : /:(nth|eq|gt|lt|first|last|even|odd)(?:\((\d*)\))?(?=[^\-]|$)/,
				PSEUDO : /:((?:[\w\u00c0-\uFFFF\-]|\\.)+)(?:\((['"]?)((?:\([^\)]+\)|[^\(\)]*)+)\2\))?/
			},
			leftMatch : {},
			attrMap : {
				"class" : "className",
				"for" : "htmlFor"
			},
			attrHandle : {
				href : function(e) {
					return e.getAttribute("href")
				},
				type : function(e) {
					return e.getAttribute("type")
				}
			},
			relative : {
				"+" : function(bP, bK) {
					var bM = typeof bK === "string", bO = bM && !bJ.test(bK), bQ = bM
							&& !bO;
					if (bO) {
						bK = bK.toLowerCase()
					}
					for ( var bL = 0, e = bP.length, bN; bL < e; bL++) {
						if ((bN = bP[bL])) {
							while ((bN = bN.previousSibling)
									&& bN.nodeType !== 1) {
							}
							bP[bL] = bQ || bN
									&& bN.nodeName.toLowerCase() === bK ? bN || false
									: bN === bK
						}
					}
					if (bQ) {
						bt.filter(bK, bP, true)
					}
				},
				">" : function(bP, bK) {
					var bO, bN = typeof bK === "string", bL = 0, e = bP.length;
					if (bN && !bJ.test(bK)) {
						bK = bK.toLowerCase();
						for (; bL < e; bL++) {
							bO = bP[bL];
							if (bO) {
								var bM = bO.parentNode;
								bP[bL] = bM.nodeName.toLowerCase() === bK ? bM
										: false
							}
						}
					} else {
						for (; bL < e; bL++) {
							bO = bP[bL];
							if (bO) {
								bP[bL] = bN ? bO.parentNode
										: bO.parentNode === bK
							}
						}
						if (bN) {
							bt.filter(bK, bP, true)
						}
					}
				},
				"" : function(bM, bK, bO) {
					var bN, bL = bC++, e = bH;
					if (typeof bK === "string" && !bJ.test(bK)) {
						bK = bK.toLowerCase();
						bN = bK;
						e = br
					}
					e("parentNode", bK, bL, bM, bN, bO)
				},
				"~" : function(bM, bK, bO) {
					var bN, bL = bC++, e = bH;
					if (typeof bK === "string" && !bJ.test(bK)) {
						bK = bK.toLowerCase();
						bN = bK;
						e = br
					}
					e("previousSibling", bK, bL, bM, bN, bO)
				}
			},
			find : {
				ID : function(bK, bL, bM) {
					if (typeof bL.getElementById !== "undefined" && !bM) {
						var e = bL.getElementById(bK[1]);
						return e && e.parentNode ? [ e ] : []
					}
				},
				NAME : function(bL, bO) {
					if (typeof bO.getElementsByName !== "undefined") {
						var bK = [], bN = bO.getElementsByName(bL[1]);
						for ( var bM = 0, e = bN.length; bM < e; bM++) {
							if (bN[bM].getAttribute("name") === bL[1]) {
								bK.push(bN[bM])
							}
						}
						return bK.length === 0 ? null : bK
					}
				},
				TAG : function(e, bK) {
					if (typeof bK.getElementsByTagName !== "undefined") {
						return bK.getElementsByTagName(e[1])
					}
				}
			},
			preFilter : {
				CLASS : function(bM, bK, bL, e, bP, bQ) {
					bM = " " + bM[1].replace(bD, "") + " ";
					if (bQ) {
						return bM
					}
					for ( var bN = 0, bO; (bO = bK[bN]) != null; bN++) {
						if (bO) {
							if (bP
									^ (bO.className && (" " + bO.className + " ")
											.replace(/[\t\n\r]/g, " ").indexOf(
													bM) >= 0)) {
								if (!bL) {
									e.push(bO)
								}
							} else {
								if (bL) {
									bK[bN] = false
								}
							}
						}
					}
					return false
				},
				ID : function(e) {
					return e[1].replace(bD, "")
				},
				TAG : function(bK, e) {
					return bK[1].replace(bD, "").toLowerCase()
				},
				CHILD : function(e) {
					if (e[1] === "nth") {
						if (!e[2]) {
							bt.error(e[0])
						}
						e[2] = e[2].replace(/^\+|\s*/g, "");
						var bK = /(-?)(\d*)(?:n([+\-]?\d*))?/
								.exec(e[2] === "even" && "2n" || e[2] === "odd"
										&& "2n+1" || !/\D/.test(e[2]) && "0n+"
										+ e[2] || e[2]);
						e[2] = (bK[1] + (bK[2] || 1)) - 0;
						e[3] = bK[3] - 0
					} else {
						if (e[2]) {
							bt.error(e[0])
						}
					}
					e[0] = bC++;
					return e
				},
				ATTR : function(bN, bK, bL, e, bO, bP) {
					var bM = bN[1] = bN[1].replace(bD, "");
					if (!bP && by.attrMap[bM]) {
						bN[1] = by.attrMap[bM]
					}
					bN[4] = (bN[4] || bN[5] || "").replace(bD, "");
					if (bN[2] === "~=") {
						bN[4] = " " + bN[4] + " "
					}
					return bN
				},
				PSEUDO : function(bN, bK, bL, e, bO) {
					if (bN[1] === "not") {
						if ((bB.exec(bN[3]) || "").length > 1
								|| /^\w/.test(bN[3])) {
							bN[3] = bt(bN[3], null, null, bK)
						} else {
							var bM = bt.filter(bN[3], bK, bL, true ^ bO);
							if (!bL) {
								e.push.apply(e, bM)
							}
							return false
						}
					} else {
						if (by.match.POS.test(bN[0])
								|| by.match.CHILD.test(bN[0])) {
							return true
						}
					}
					return bN
				},
				POS : function(e) {
					e.unshift(true);
					return e
				}
			},
			filters : {
				enabled : function(e) {
					return e.disabled === false && e.type !== "hidden"
				},
				disabled : function(e) {
					return e.disabled === true
				},
				checked : function(e) {
					return e.checked === true
				},
				selected : function(e) {
					if (e.parentNode) {
						e.parentNode.selectedIndex
					}
					return e.selected === true
				},
				parent : function(e) {
					return !!e.firstChild
				},
				empty : function(e) {
					return !e.firstChild
				},
				has : function(bL, bK, e) {
					return !!bt(e[3], bL).length
				},
				header : function(e) {
					return (/h\d/i).test(e.nodeName)
				},
				text : function(bL) {
					var e = bL.getAttribute("type"), bK = bL.type;
					return bL.nodeName.toLowerCase() === "input"
							&& "text" === bK && (e === bK || e === null)
				},
				radio : function(e) {
					return e.nodeName.toLowerCase() === "input"
							&& "radio" === e.type
				},
				checkbox : function(e) {
					return e.nodeName.toLowerCase() === "input"
							&& "checkbox" === e.type
				},
				file : function(e) {
					return e.nodeName.toLowerCase() === "input"
							&& "file" === e.type
				},
				password : function(e) {
					return e.nodeName.toLowerCase() === "input"
							&& "password" === e.type
				},
				submit : function(bK) {
					var e = bK.nodeName.toLowerCase();
					return (e === "input" || e === "button")
							&& "submit" === bK.type
				},
				image : function(e) {
					return e.nodeName.toLowerCase() === "input"
							&& "image" === e.type
				},
				reset : function(bK) {
					var e = bK.nodeName.toLowerCase();
					return (e === "input" || e === "button")
							&& "reset" === bK.type
				},
				button : function(bK) {
					var e = bK.nodeName.toLowerCase();
					return e === "input" && "button" === bK.type
							|| e === "button"
				},
				input : function(e) {
					return (/input|select|textarea|button/i).test(e.nodeName)
				},
				focus : function(e) {
					return e === e.ownerDocument.activeElement
				}
			},
			setFilters : {
				first : function(bK, e) {
					return e === 0
				},
				last : function(bL, bK, e, bM) {
					return bK === bM.length - 1
				},
				even : function(bK, e) {
					return e % 2 === 0
				},
				odd : function(bK, e) {
					return e % 2 === 1
				},
				lt : function(bL, bK, e) {
					return bK < e[3] - 0
				},
				gt : function(bL, bK, e) {
					return bK > e[3] - 0
				},
				nth : function(bL, bK, e) {
					return e[3] - 0 === bK
				},
				eq : function(bL, bK, e) {
					return e[3] - 0 === bK
				}
			},
			filter : {
				PSEUDO : function(bL, bQ, bP, bR) {
					var e = bQ[1], bK = by.filters[e];
					if (bK) {
						return bK(bL, bP, bQ, bR)
					} else {
						if (e === "contains") {
							return (bL.textContent || bL.innerText
									|| bt.getText( [ bL ]) || "")
									.indexOf(bQ[3]) >= 0
						} else {
							if (e === "not") {
								var bM = bQ[3];
								for ( var bO = 0, bN = bM.length; bO < bN; bO++) {
									if (bM[bO] === bL) {
										return false
									}
								}
								return true
							} else {
								bt.error(e)
							}
						}
					}
				},
				CHILD : function(e, bM) {
					var bP = bM[1], bK = e;
					switch (bP) {
					case "only":
					case "first":
						while ((bK = bK.previousSibling)) {
							if (bK.nodeType === 1) {
								return false
							}
						}
						if (bP === "first") {
							return true
						}
						bK = e;
					case "last":
						while ((bK = bK.nextSibling)) {
							if (bK.nodeType === 1) {
								return false
							}
						}
						return true;
					case "nth":
						var bL = bM[2], bS = bM[3];
						if (bL === 1 && bS === 0) {
							return true
						}
						var bO = bM[0], bR = e.parentNode;
						if (bR && (bR.sizcache !== bO || !e.nodeIndex)) {
							var bN = 0;
							for (bK = bR.firstChild; bK; bK = bK.nextSibling) {
								if (bK.nodeType === 1) {
									bK.nodeIndex = ++bN
								}
							}
							bR.sizcache = bO
						}
						var bQ = e.nodeIndex - bS;
						if (bL === 0) {
							return bQ === 0
						} else {
							return (bQ % bL === 0 && bQ / bL >= 0)
						}
					}
				},
				ID : function(bK, e) {
					return bK.nodeType === 1 && bK.getAttribute("id") === e
				},
				TAG : function(bK, e) {
					return (e === "*" && bK.nodeType === 1)
							|| bK.nodeName.toLowerCase() === e
				},
				CLASS : function(bK, e) {
					return (" " + (bK.className || bK.getAttribute("class")) + " ")
							.indexOf(e) > -1
				},
				ATTR : function(bO, bM) {
					var bL = bM[1], e = by.attrHandle[bL] ? by.attrHandle[bL]
							(bO) : bO[bL] != null ? bO[bL] : bO
							.getAttribute(bL), bP = e + "", bN = bM[2], bK = bM[4];
					return e == null ? bN === "!="
							: bN === "=" ? bP === bK
									: bN === "*=" ? bP.indexOf(bK) >= 0
											: bN === "~=" ? (" " + bP + " ")
													.indexOf(bK) >= 0
													: !bK ? bP && e !== false
															: bN === "!=" ? bP !== bK
																	: bN === "^=" ? bP
																			.indexOf(bK) === 0
																			: bN === "$=" ? bP
																					.substr(bP.length
																							- bK.length) === bK
																					: bN === "|=" ? bP === bK
																							|| bP
																									.substr(
																											0,
																											bK.length + 1) === bK
																									+ "-"
																							: false
				},
				POS : function(bN, bK, bL, bO) {
					var e = bK[2], bM = by.setFilters[e];
					if (bM) {
						return bM(bN, bL, bK, bO)
					}
				}
			}
		};
		var bx = by.match.POS, bs = function(bK, e) {
			return "\\" + (e - 0 + 1)
		};
		for ( var bu in by.match) {
			by.match[bu] = new RegExp(by.match[bu].source
					+ (/(?![^\[]*\])(?![^\(]*\))/.source));
			by.leftMatch[bu] = new RegExp(/(^(?:.|\r|\n)*?)/.source
					+ by.match[bu].source.replace(/\\(\d+)/g, bs))
		}
		var bz = function(bK, e) {
			bK = Array.prototype.slice.call(bK, 0);
			if (e) {
				e.push.apply(e, bK);
				return e
			}
			return bK
		};
		try {
			Array.prototype.slice.call(ap.documentElement.childNodes, 0)[0].nodeType
		} catch (bI) {
			bz = function(bN, bM) {
				var bL = 0, bK = bM || [];
				if (bF.call(bN) === "[object Array]") {
					Array.prototype.push.apply(bK, bN)
				} else {
					if (typeof bN.length === "number") {
						for ( var e = bN.length; bL < e; bL++) {
							bK.push(bN[bL])
						}
					} else {
						for (; bN[bL]; bL++) {
							bK.push(bN[bL])
						}
					}
				}
				return bK
			}
		}
		var bE, bA;
		if (ap.documentElement.compareDocumentPosition) {
			bE = function(bK, e) {
				if (bK === e) {
					bw = true;
					return 0
				}
				if (!bK.compareDocumentPosition || !e.compareDocumentPosition) {
					return bK.compareDocumentPosition ? -1 : 1
				}
				return bK.compareDocumentPosition(e) & 4 ? -1 : 1
			}
		} else {
			bE = function(bR, bQ) {
				if (bR === bQ) {
					bw = true;
					return 0
				} else {
					if (bR.sourceIndex && bQ.sourceIndex) {
						return bR.sourceIndex - bQ.sourceIndex
					}
				}
				var bO, bK, bL = [], e = [], bN = bR.parentNode, bP = bQ.parentNode, bS = bN;
				if (bN === bP) {
					return bA(bR, bQ)
				} else {
					if (!bN) {
						return -1
					} else {
						if (!bP) {
							return 1
						}
					}
				}
				while (bS) {
					bL.unshift(bS);
					bS = bS.parentNode
				}
				bS = bP;
				while (bS) {
					e.unshift(bS);
					bS = bS.parentNode
				}
				bO = bL.length;
				bK = e.length;
				for ( var bM = 0; bM < bO && bM < bK; bM++) {
					if (bL[bM] !== e[bM]) {
						return bA(bL[bM], e[bM])
					}
				}
				return bM === bO ? bA(bR, e[bM], -1) : bA(bL[bM], bQ, 1)
			};
			bA = function(bK, e, bL) {
				if (bK === e) {
					return bL
				}
				var bM = bK.nextSibling;
				while (bM) {
					if (bM === e) {
						return -1
					}
					bM = bM.nextSibling
				}
				return 1
			}
		}
		bt.getText = function(e) {
			var bK = "", bM;
			for ( var bL = 0; e[bL]; bL++) {
				bM = e[bL];
				if (bM.nodeType === 3 || bM.nodeType === 4) {
					bK += bM.nodeValue
				} else {
					if (bM.nodeType !== 8) {
						bK += bt.getText(bM.childNodes)
					}
				}
			}
			return bK
		};
		(function() {
			var bK = ap.createElement("div"), bL = "script"
					+ (new Date()).getTime(), e = ap.documentElement;
			bK.innerHTML = "<a name='" + bL + "'/>";
			e.insertBefore(bK, e.firstChild);
			if (ap.getElementById(bL)) {
				by.find.ID = function(bN, bO, bP) {
					if (typeof bO.getElementById !== "undefined" && !bP) {
						var bM = bO.getElementById(bN[1]);
						return bM ? bM.id === bN[1]
								|| typeof bM.getAttributeNode !== "undefined"
								&& bM.getAttributeNode("id").nodeValue === bN[1] ? [ bM ]
								: K
								: []
					}
				};
				by.filter.ID = function(bO, bM) {
					var bN = typeof bO.getAttributeNode !== "undefined"
							&& bO.getAttributeNode("id");
					return bO.nodeType === 1 && bN && bN.nodeValue === bM
				}
			}
			e.removeChild(bK);
			e = bK = null
		})();
		(function() {
			var e = ap.createElement("div");
			e.appendChild(ap.createComment(""));
			if (e.getElementsByTagName("*").length > 0) {
				by.find.TAG = function(bK, bO) {
					var bN = bO.getElementsByTagName(bK[1]);
					if (bK[1] === "*") {
						var bM = [];
						for ( var bL = 0; bN[bL]; bL++) {
							if (bN[bL].nodeType === 1) {
								bM.push(bN[bL])
							}
						}
						bN = bM
					}
					return bN
				}
			}
			e.innerHTML = "<a href='#'></a>";
			if (e.firstChild
					&& typeof e.firstChild.getAttribute !== "undefined"
					&& e.firstChild.getAttribute("href") !== "#") {
				by.attrHandle.href = function(bK) {
					return bK.getAttribute("href", 2)
				}
			}
			e = null
		})();
		if (ap.querySelectorAll) {
			(function() {
				var e = bt, bM = ap.createElement("div"), bL = "__sizzle__";
				bM.innerHTML = "<p class='TEST'></p>";
				if (bM.querySelectorAll
						&& bM.querySelectorAll(".TEST").length === 0) {
					return
				}
				bt = function(bX, bO, bS, bW) {
					bO = bO || ap;
					if (!bW && !bt.isXML(bO)) {
						var bV = /^(\w+$)|^\.([\w\-]+$)|^#([\w\-]+$)/.exec(bX);
						if (bV && (bO.nodeType === 1 || bO.nodeType === 9)) {
							if (bV[1]) {
								return bz(bO.getElementsByTagName(bX), bS)
							} else {
								if (bV[2] && by.find.CLASS
										&& bO.getElementsByClassName) {
									return bz(bO.getElementsByClassName(bV[2]),
											bS)
								}
							}
						}
						if (bO.nodeType === 9) {
							if (bX === "body" && bO.body) {
								return bz( [ bO.body ], bS)
							} else {
								if (bV && bV[3]) {
									var bR = bO.getElementById(bV[3]);
									if (bR && bR.parentNode) {
										if (bR.id === bV[3]) {
											return bz( [ bR ], bS)
										}
									} else {
										return bz( [], bS)
									}
								}
							}
							try {
								return bz(bO.querySelectorAll(bX), bS)
							} catch (bT) {
							}
						} else {
							if (bO.nodeType === 1
									&& bO.nodeName.toLowerCase() !== "object") {
								var bP = bO, bQ = bO.getAttribute("id"), bN = bQ
										|| bL, bZ = bO.parentNode, bY = /^\s*[+~]/
										.test(bX);
								if (!bQ) {
									bO.setAttribute("id", bN)
								} else {
									bN = bN.replace(/'/g, "\\$&")
								}
								if (bY && bZ) {
									bO = bO.parentNode
								}
								try {
									if (!bY || bZ) {
										return bz(bO.querySelectorAll("[id='"
												+ bN + "'] " + bX), bS)
									}
								} catch (bU) {
								} finally {
									if (!bQ) {
										bP.removeAttribute("id")
									}
								}
							}
						}
					}
					return e(bX, bO, bS, bW)
				};
				for ( var bK in e) {
					bt[bK] = e[bK]
				}
				bM = null
			})()
		}
		(function() {
			var e = ap.documentElement, bL = e.matchesSelector
					|| e.mozMatchesSelector || e.webkitMatchesSelector
					|| e.msMatchesSelector;
			if (bL) {
				var bN = !bL.call(ap.createElement("div"), "div"), bK = false;
				try {
					bL.call(ap.documentElement, "[test!='']:sizzle")
				} catch (bM) {
					bK = true
				}
				bt.matchesSelector = function(bP, bR) {
					bR = bR.replace(/\=\s*([^'"\]]*)\s*\]/g, "='$1']");
					if (!bt.isXML(bP)) {
						try {
							if (bK || !by.match.PSEUDO.test(bR)
									&& !/!=/.test(bR)) {
								var bO = bL.call(bP, bR);
								if (bO || !bN || bP.document
										&& bP.document.nodeType !== 11) {
									return bO
								}
							}
						} catch (bQ) {
						}
					}
					return bt(bR, null, null, [ bP ]).length > 0
				}
			}
		})();
		(function() {
			var e = ap.createElement("div");
			e.innerHTML = "<div class='test e'></div><div class='test'></div>";
			if (!e.getElementsByClassName
					|| e.getElementsByClassName("e").length === 0) {
				return
			}
			e.lastChild.className = "e";
			if (e.getElementsByClassName("e").length === 1) {
				return
			}
			by.order.splice(1, 0, "CLASS");
			by.find.CLASS = function(bK, bL, bM) {
				if (typeof bL.getElementsByClassName !== "undefined" && !bM) {
					return bL.getElementsByClassName(bK[1])
				}
			};
			e = null
		})();
		function br(bK, bP, bO, bS, bQ, bR) {
			for ( var bM = 0, bL = bS.length; bM < bL; bM++) {
				var e = bS[bM];
				if (e) {
					var bN = false;
					e = e[bK];
					while (e) {
						if (e.sizcache === bO) {
							bN = bS[e.sizset];
							break
						}
						if (e.nodeType === 1 && !bR) {
							e.sizcache = bO;
							e.sizset = bM
						}
						if (e.nodeName.toLowerCase() === bP) {
							bN = e;
							break
						}
						e = e[bK]
					}
					bS[bM] = bN
				}
			}
		}
		function bH(bK, bP, bO, bS, bQ, bR) {
			for ( var bM = 0, bL = bS.length; bM < bL; bM++) {
				var e = bS[bM];
				if (e) {
					var bN = false;
					e = e[bK];
					while (e) {
						if (e.sizcache === bO) {
							bN = bS[e.sizset];
							break
						}
						if (e.nodeType === 1) {
							if (!bR) {
								e.sizcache = bO;
								e.sizset = bM
							}
							if (typeof bP !== "string") {
								if (e === bP) {
									bN = true;
									break
								}
							} else {
								if (bt.filter(bP, [ e ]).length > 0) {
									bN = e;
									break
								}
							}
						}
						e = e[bK]
					}
					bS[bM] = bN
				}
			}
		}
		if (ap.documentElement.contains) {
			bt.contains = function(bK, e) {
				return bK !== e && (bK.contains ? bK.contains(e) : true)
			}
		} else {
			if (ap.documentElement.compareDocumentPosition) {
				bt.contains = function(bK, e) {
					return !!(bK.compareDocumentPosition(e) & 16)
				}
			} else {
				bt.contains = function() {
					return false
				}
			}
		}
		bt.isXML = function(e) {
			var bK = (e ? e.ownerDocument || e : 0).documentElement;
			return bK ? bK.nodeName !== "HTML" : false
		};
		var bG = function(e, bQ) {
			var bO, bM = [], bN = "", bL = bQ.nodeType ? [ bQ ] : bQ;
			while ((bO = by.match.PSEUDO.exec(e))) {
				bN += bO[0];
				e = e.replace(by.match.PSEUDO, "")
			}
			e = by.relative[e] ? e + "*" : e;
			for ( var bP = 0, bK = bL.length; bP < bK; bP++) {
				bt(e, bL[bP], bM)
			}
			return bt.filter(bN, bM)
		};
		b.find = bt;
		b.expr = bt.selectors;
		b.expr[":"] = b.expr.filters;
		b.unique = bt.uniqueSort;
		b.text = bt.getText;
		b.isXMLDoc = bt.isXML;
		b.contains = bt.contains
	})();
	var X = /Until$/, al = /^(?:parents|prevUntil|prevAll)/, a5 = /,/, bm = /^.[^:#\[\.,]*$/, P = Array.prototype.slice, H = b.expr.match.POS, at = {
		children : true,
		contents : true,
		next : true,
		prev : true
	};
	b.fn
			.extend( {
				find : function(e) {
					var bs = this, bu, br;
					if (typeof e !== "string") {
						return b(e).filter(function() {
							for (bu = 0, br = bs.length; bu < br; bu++) {
								if (b.contains(bs[bu], this)) {
									return true
								}
							}
						})
					}
					var bt = this.pushStack("", "find", e), bw, bx, bv;
					for (bu = 0, br = this.length; bu < br; bu++) {
						bw = bt.length;
						b.find(e, this[bu], bt);
						if (bu > 0) {
							for (bx = bw; bx < bt.length; bx++) {
								for (bv = 0; bv < bw; bv++) {
									if (bt[bv] === bt[bx]) {
										bt.splice(bx--, 1);
										break
									}
								}
							}
						}
					}
					return bt
				},
				has : function(br) {
					var e = b(br);
					return this.filter(function() {
						for ( var bt = 0, bs = e.length; bt < bs; bt++) {
							if (b.contains(this, e[bt])) {
								return true
							}
						}
					})
				},
				not : function(e) {
					return this.pushStack(aA(this, e, false), "not", e)
				},
				filter : function(e) {
					return this.pushStack(aA(this, e, true), "filter", e)
				},
				is : function(e) {
					return !!e
							&& (typeof e === "string" ? b.filter(e, this).length > 0
									: this.filter(e).length > 0)
				},
				closest : function(bA, br) {
					var bx = [], bu, bs, bz = this[0];
					if (b.isArray(bA)) {
						var bw, bt, bv = {}, e = 1;
						if (bz && bA.length) {
							for (bu = 0, bs = bA.length; bu < bs; bu++) {
								bt = bA[bu];
								if (!bv[bt]) {
									bv[bt] = H.test(bt) ? b(bt, br
											|| this.context) : bt
								}
							}
							while (bz && bz.ownerDocument && bz !== br) {
								for (bt in bv) {
									bw = bv[bt];
									if (bw.jquery ? bw.index(bz) > -1 : b(bz)
											.is(bw)) {
										bx.push( {
											selector : bt,
											elem : bz,
											level : e
										})
									}
								}
								bz = bz.parentNode;
								e++
							}
						}
						return bx
					}
					var by = H.test(bA) || typeof bA !== "string" ? b(bA, br
							|| this.context) : 0;
					for (bu = 0, bs = this.length; bu < bs; bu++) {
						bz = this[bu];
						while (bz) {
							if (by ? by.index(bz) > -1 : b.find
									.matchesSelector(bz, bA)) {
								bx.push(bz);
								break
							} else {
								bz = bz.parentNode;
								if (!bz || !bz.ownerDocument || bz === br
										|| bz.nodeType === 11) {
									break
								}
							}
						}
					}
					bx = bx.length > 1 ? b.unique(bx) : bx;
					return this.pushStack(bx, "closest", bA)
				},
				index : function(e) {
					if (!e) {
						return (this[0] && this[0].parentNode) ? this.prevAll().length
								: -1
					}
					if (typeof e === "string") {
						return b.inArray(this[0], b(e))
					}
					return b.inArray(e.jquery ? e[0] : e, this)
				},
				add : function(e, br) {
					var bt = typeof e === "string" ? b(e, br) : b.makeArray(e
							&& e.nodeType ? [ e ] : e), bs = b.merge(
							this.get(), bt);
					return this.pushStack(C(bt[0]) || C(bs[0]) ? bs : b
							.unique(bs))
				},
				andSelf : function() {
					return this.add(this.prevObject)
				}
			});
	function C(e) {
		return !e || !e.parentNode || e.parentNode.nodeType === 11
	}
	b.each( {
		parent : function(br) {
			var e = br.parentNode;
			return e && e.nodeType !== 11 ? e : null
		},
		parents : function(e) {
			return b.dir(e, "parentNode")
		},
		parentsUntil : function(br, e, bs) {
			return b.dir(br, "parentNode", bs)
		},
		next : function(e) {
			return b.nth(e, 2, "nextSibling")
		},
		prev : function(e) {
			return b.nth(e, 2, "previousSibling")
		},
		nextAll : function(e) {
			return b.dir(e, "nextSibling")
		},
		prevAll : function(e) {
			return b.dir(e, "previousSibling")
		},
		nextUntil : function(br, e, bs) {
			return b.dir(br, "nextSibling", bs)
		},
		prevUntil : function(br, e, bs) {
			return b.dir(br, "previousSibling", bs)
		},
		siblings : function(e) {
			return b.sibling(e.parentNode.firstChild, e)
		},
		children : function(e) {
			return b.sibling(e.firstChild)
		},
		contents : function(e) {
			return b.nodeName(e, "iframe") ? e.contentDocument
					|| e.contentWindow.document : b.makeArray(e.childNodes)
		}
	}, function(e, br) {
		b.fn[e] = function(bv, bs) {
			var bu = b.map(this, br, bv), bt = P.call(arguments);
			if (!X.test(e)) {
				bs = bv
			}
			if (bs && typeof bs === "string") {
				bu = b.filter(bs, bu)
			}
			bu = this.length > 1 && !at[e] ? b.unique(bu) : bu;
			if ((this.length > 1 || a5.test(bs)) && al.test(e)) {
				bu = bu.reverse()
			}
			return this.pushStack(bu, e, bt.join(","))
		}
	});
	b.extend( {
		filter : function(bs, e, br) {
			if (br) {
				bs = ":not(" + bs + ")"
			}
			return e.length === 1 ? b.find.matchesSelector(e[0], bs) ? [ e[0] ]
					: [] : b.find.matches(bs, e)
		},
		dir : function(bs, br, bu) {
			var e = [], bt = bs[br];
			while (bt && bt.nodeType !== 9
					&& (bu === K || bt.nodeType !== 1 || !b(bt).is(bu))) {
				if (bt.nodeType === 1) {
					e.push(bt)
				}
				bt = bt[br]
			}
			return e
		},
		nth : function(bu, e, bs, bt) {
			e = e || 1;
			var br = 0;
			for (; bu; bu = bu[bs]) {
				if (bu.nodeType === 1 && ++br === e) {
					break
				}
			}
			return bu
		},
		sibling : function(bs, br) {
			var e = [];
			for (; bs; bs = bs.nextSibling) {
				if (bs.nodeType === 1 && bs !== br) {
					e.push(bs)
				}
			}
			return e
		}
	});
	function aA(bt, bs, e) {
		bs = bs || 0;
		if (b.isFunction(bs)) {
			return b.grep(bt, function(bv, bu) {
				var bw = !!bs.call(bv, bu, bv);
				return bw === e
			})
		} else {
			if (bs.nodeType) {
				return b.grep(bt, function(bv, bu) {
					return (bv === bs) === e
				})
			} else {
				if (typeof bs === "string") {
					var br = b.grep(bt, function(bu) {
						return bu.nodeType === 1
					});
					if (bm.test(bs)) {
						return b.filter(bs, br, !e)
					} else {
						bs = b.filter(bs, br)
					}
				}
			}
		}
		return b.grep(bt, function(bv, bu) {
			return (b.inArray(bv, bs) >= 0) === e
		})
	}
	var ac = / jQuery\d+="(?:\d+|null)"/g, am = /^\s+/, R = /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/ig, d = /<([\w:]+)/, w = /<tbody/i, U = /<|&#?\w+;/, O = /<(?:script|object|embed|option|style)/i, n = /checked\s*(?:[^=]|=\s*.checked.)/i, bi = /\/(java|ecma)script/i, aI = /^\s*<!(?:\[CDATA\[|\-\-)/, ar = {
		option : [ 1, "<select multiple='multiple'>", "</select>" ],
		legend : [ 1, "<fieldset>", "</fieldset>" ],
		thead : [ 1, "<table>", "</table>" ],
		tr : [ 2, "<table><tbody>", "</tbody></table>" ],
		td : [ 3, "<table><tbody><tr>", "</tr></tbody></table>" ],
		col : [ 2, "<table><tbody></tbody><colgroup>", "</colgroup></table>" ],
		area : [ 1, "<map>", "</map>" ],
		_default : [ 0, "", "" ]
	};
	ar.optgroup = ar.option;
	ar.tbody = ar.tfoot = ar.colgroup = ar.caption = ar.thead;
	ar.th = ar.td;
	if (!b.support.htmlSerialize) {
		ar._default = [ 1, "div<div>", "</div>" ]
	}
	b.fn
			.extend( {
				text : function(e) {
					if (b.isFunction(e)) {
						return this.each(function(bs) {
							var br = b(this);
							br.text(e.call(this, bs, br.text()))
						})
					}
					if (typeof e !== "object" && e !== K) {
						return this.empty().append(
								(this[0] && this[0].ownerDocument || ap)
										.createTextNode(e))
					}
					return b.text(this)
				},
				wrapAll : function(e) {
					if (b.isFunction(e)) {
						return this.each(function(bs) {
							b(this).wrapAll(e.call(this, bs))
						})
					}
					if (this[0]) {
						var br = b(e, this[0].ownerDocument).eq(0).clone(true);
						if (this[0].parentNode) {
							br.insertBefore(this[0])
						}
						br.map(
								function() {
									var bs = this;
									while (bs.firstChild
											&& bs.firstChild.nodeType === 1) {
										bs = bs.firstChild
									}
									return bs
								}).append(this)
					}
					return this
				},
				wrapInner : function(e) {
					if (b.isFunction(e)) {
						return this.each(function(br) {
							b(this).wrapInner(e.call(this, br))
						})
					}
					return this.each(function() {
						var br = b(this), bs = br.contents();
						if (bs.length) {
							bs.wrapAll(e)
						} else {
							br.append(e)
						}
					})
				},
				wrap : function(e) {
					return this.each(function() {
						b(this).wrapAll(e)
					})
				},
				unwrap : function() {
					return this.parent().each(function() {
						if (!b.nodeName(this, "body")) {
							b(this).replaceWith(this.childNodes)
						}
					}).end()
				},
				append : function() {
					return this.domManip(arguments, true, function(e) {
						if (this.nodeType === 1) {
							this.appendChild(e)
						}
					})
				},
				prepend : function() {
					return this.domManip(arguments, true, function(e) {
						if (this.nodeType === 1) {
							this.insertBefore(e, this.firstChild)
						}
					})
				},
				before : function() {
					if (this[0] && this[0].parentNode) {
						return this.domManip(arguments, false, function(br) {
							this.parentNode.insertBefore(br, this)
						})
					} else {
						if (arguments.length) {
							var e = b(arguments[0]);
							e.push.apply(e, this.toArray());
							return this.pushStack(e, "before", arguments)
						}
					}
				},
				after : function() {
					if (this[0] && this[0].parentNode) {
						return this.domManip(arguments, false, function(br) {
							this.parentNode.insertBefore(br, this.nextSibling)
						})
					} else {
						if (arguments.length) {
							var e = this.pushStack(this, "after", arguments);
							e.push.apply(e, b(arguments[0]).toArray());
							return e
						}
					}
				},
				remove : function(e, bt) {
					for ( var br = 0, bs; (bs = this[br]) != null; br++) {
						if (!e || b.filter(e, [ bs ]).length) {
							if (!bt && bs.nodeType === 1) {
								b.cleanData(bs.getElementsByTagName("*"));
								b.cleanData( [ bs ])
							}
							if (bs.parentNode) {
								bs.parentNode.removeChild(bs)
							}
						}
					}
					return this
				},
				empty : function() {
					for ( var e = 0, br; (br = this[e]) != null; e++) {
						if (br.nodeType === 1) {
							b.cleanData(br.getElementsByTagName("*"))
						}
						while (br.firstChild) {
							br.removeChild(br.firstChild)
						}
					}
					return this
				},
				clone : function(br, e) {
					br = br == null ? false : br;
					e = e == null ? br : e;
					return this.map(function() {
						return b.clone(this, br, e)
					})
				},
				html : function(bt) {
					if (bt === K) {
						return this[0] && this[0].nodeType === 1 ? this[0].innerHTML
								.replace(ac, "")
								: null
					} else {
						if (typeof bt === "string"
								&& !O.test(bt)
								&& (b.support.leadingWhitespace || !am.test(bt))
								&& !ar[(d.exec(bt) || [ "", "" ])[1]
										.toLowerCase()]) {
							bt = bt.replace(R, "<$1></$2>");
							try {
								for ( var bs = 0, br = this.length; bs < br; bs++) {
									if (this[bs].nodeType === 1) {
										b.cleanData(this[bs]
												.getElementsByTagName("*"));
										this[bs].innerHTML = bt
									}
								}
							} catch (bu) {
								this.empty().append(bt)
							}
						} else {
							if (b.isFunction(bt)) {
								this.each(function(bv) {
									var e = b(this);
									e.html(bt.call(this, bv, e.html()))
								})
							} else {
								this.empty().append(bt)
							}
						}
					}
					return this
				},
				replaceWith : function(e) {
					if (this[0] && this[0].parentNode) {
						if (b.isFunction(e)) {
							return this.each(function(bt) {
								var bs = b(this), br = bs.html();
								bs.replaceWith(e.call(this, bt, br))
							})
						}
						if (typeof e !== "string") {
							e = b(e).detach()
						}
						return this.each(function() {
							var bs = this.nextSibling, br = this.parentNode;
							b(this).remove();
							if (bs) {
								b(bs).before(e)
							} else {
								b(br).append(e)
							}
						})
					} else {
						return this.length ? this.pushStack(
								b(b.isFunction(e) ? e() : e), "replaceWith", e)
								: this
					}
				},
				detach : function(e) {
					return this.remove(e, true)
				},
				domManip : function(bx, bB, bA) {
					var bt, bu, bw, bz, by = bx[0], br = [];
					if (!b.support.checkClone && arguments.length === 3
							&& typeof by === "string" && n.test(by)) {
						return this.each(function() {
							b(this).domManip(bx, bB, bA, true)
						})
					}
					if (b.isFunction(by)) {
						return this.each(function(bD) {
							var bC = b(this);
							bx[0] = by.call(this, bD, bB ? bC.html() : K);
							bC.domManip(bx, bB, bA)
						})
					}
					if (this[0]) {
						bz = by && by.parentNode;
						if (b.support.parentNode && bz && bz.nodeType === 11
								&& bz.childNodes.length === this.length) {
							bt = {
								fragment : bz
							}
						} else {
							bt = b.buildFragment(bx, this, br)
						}
						bw = bt.fragment;
						if (bw.childNodes.length === 1) {
							bu = bw = bw.firstChild
						} else {
							bu = bw.firstChild
						}
						if (bu) {
							bB = bB && b.nodeName(bu, "tr");
							for ( var bs = 0, e = this.length, bv = e - 1; bs < e; bs++) {
								bA.call(bB ? a6(this[bs], bu) : this[bs],
										bt.cacheable || (e > 1 && bs < bv) ? b
												.clone(bw, true, true) : bw)
							}
						}
						if (br.length) {
							b.each(br, bl)
						}
					}
					return this
				}
			});
	function a6(e, br) {
		return b.nodeName(e, "table") ? (e.getElementsByTagName("tbody")[0] || e
				.appendChild(e.ownerDocument.createElement("tbody")))
				: e
	}
	function t(e, bx) {
		if (bx.nodeType !== 1 || !b.hasData(e)) {
			return
		}
		var bw = b.expando, bt = b.data(e), bu = b.data(bx, bt);
		if ((bt = bt[bw])) {
			var by = bt.events;
			bu = bu[bw] = b.extend( {}, bt);
			if (by) {
				delete bu.handle;
				bu.events = {};
				for ( var bv in by) {
					for ( var bs = 0, br = by[bv].length; bs < br; bs++) {
						b.event.add(bx, bv + (by[bv][bs].namespace ? "." : "")
								+ by[bv][bs].namespace, by[bv][bs],
								by[bv][bs].data)
					}
				}
			}
		}
	}
	function ad(br, e) {
		var bs;
		if (e.nodeType !== 1) {
			return
		}
		if (e.clearAttributes) {
			e.clearAttributes()
		}
		if (e.mergeAttributes) {
			e.mergeAttributes(br)
		}
		bs = e.nodeName.toLowerCase();
		if (bs === "object") {
			e.outerHTML = br.outerHTML
		} else {
			if (bs === "input"
					&& (br.type === "checkbox" || br.type === "radio")) {
				if (br.checked) {
					e.defaultChecked = e.checked = br.checked
				}
				if (e.value !== br.value) {
					e.value = br.value
				}
			} else {
				if (bs === "option") {
					e.selected = br.defaultSelected
				} else {
					if (bs === "input" || bs === "textarea") {
						e.defaultValue = br.defaultValue
					}
				}
			}
		}
		e.removeAttribute(b.expando)
	}
	b.buildFragment = function(bv, bt, br) {
		var bu, e, bs, bw;
		if (bt && bt[0]) {
			bw = bt[0].ownerDocument || bt[0]
		}
		if (!bw.createDocumentFragment) {
			bw = ap
		}
		if (bv.length === 1 && typeof bv[0] === "string" && bv[0].length < 512
				&& bw === ap && bv[0].charAt(0) === "<" && !O.test(bv[0])
				&& (b.support.checkClone || !n.test(bv[0]))) {
			e = true;
			bs = b.fragments[bv[0]];
			if (bs && bs !== 1) {
				bu = bs
			}
		}
		if (!bu) {
			bu = bw.createDocumentFragment();
			b.clean(bv, bw, bu, br)
		}
		if (e) {
			b.fragments[bv[0]] = bs ? bu : 1
		}
		return {
			fragment : bu,
			cacheable : e
		}
	};
	b.fragments = {};
	b.each( {
		appendTo : "append",
		prependTo : "prepend",
		insertBefore : "before",
		insertAfter : "after",
		replaceAll : "replaceWith"
	}, function(e, br) {
		b.fn[e] = function(bs) {
			var bv = [], by = b(bs), bx = this.length === 1
					&& this[0].parentNode;
			if (bx && bx.nodeType === 11 && bx.childNodes.length === 1
					&& by.length === 1) {
				by[br](this[0]);
				return this
			} else {
				for ( var bw = 0, bt = by.length; bw < bt; bw++) {
					var bu = (bw > 0 ? this.clone(true) : this).get();
					b(by[bw])[br](bu);
					bv = bv.concat(bu)
				}
				return this.pushStack(bv, e, by.selector)
			}
		}
	});
	function bb(e) {
		if ("getElementsByTagName" in e) {
			return e.getElementsByTagName("*")
		} else {
			if ("querySelectorAll" in e) {
				return e.querySelectorAll("*")
			} else {
				return []
			}
		}
	}
	function au(e) {
		if (e.type === "checkbox" || e.type === "radio") {
			e.defaultChecked = e.checked
		}
	}
	function E(e) {
		if (b.nodeName(e, "input")) {
			au(e)
		} else {
			if ("getElementsByTagName" in e) {
				b.grep(e.getElementsByTagName("input"), au)
			}
		}
	}
	b
			.extend( {
				clone : function(bu, bw, bs) {
					var bv = bu.cloneNode(true), e, br, bt;
					if ((!b.support.noCloneEvent || !b.support.noCloneChecked)
							&& (bu.nodeType === 1 || bu.nodeType === 11)
							&& !b.isXMLDoc(bu)) {
						ad(bu, bv);
						e = bb(bu);
						br = bb(bv);
						for (bt = 0; e[bt]; ++bt) {
							if (br[bt]) {
								ad(e[bt], br[bt])
							}
						}
					}
					if (bw) {
						t(bu, bv);
						if (bs) {
							e = bb(bu);
							br = bb(bv);
							for (bt = 0; e[bt]; ++bt) {
								t(e[bt], br[bt])
							}
						}
					}
					e = br = null;
					return bv
				},
				clean : function(bs, bu, bD, bw) {
					var bB;
					bu = bu || ap;
					if (typeof bu.createElement === "undefined") {
						bu = bu.ownerDocument || bu[0] && bu[0].ownerDocument
								|| ap
					}
					var bE = [], bx;
					for ( var bA = 0, bv; (bv = bs[bA]) != null; bA++) {
						if (typeof bv === "number") {
							bv += ""
						}
						if (!bv) {
							continue
						}
						if (typeof bv === "string") {
							if (!U.test(bv)) {
								bv = bu.createTextNode(bv)
							} else {
								bv = bv.replace(R, "<$1></$2>");
								var bG = (d.exec(bv) || [ "", "" ])[1]
										.toLowerCase(), bt = ar[bG]
										|| ar._default, bz = bt[0], br = bu
										.createElement("div");
								br.innerHTML = bt[1] + bv + bt[2];
								while (bz--) {
									br = br.lastChild
								}
								if (!b.support.tbody) {
									var e = w.test(bv), by = bG === "table"
											&& !e ? br.firstChild
											&& br.firstChild.childNodes
											: bt[1] === "<table>" && !e ? br.childNodes
													: [];
									for (bx = by.length - 1; bx >= 0; --bx) {
										if (b.nodeName(by[bx], "tbody")
												&& !by[bx].childNodes.length) {
											by[bx].parentNode
													.removeChild(by[bx])
										}
									}
								}
								if (!b.support.leadingWhitespace && am.test(bv)) {
									br.insertBefore(bu.createTextNode(am
											.exec(bv)[0]), br.firstChild)
								}
								bv = br.childNodes
							}
						}
						var bC;
						if (!b.support.appendChecked) {
							if (bv[0] && typeof (bC = bv.length) === "number") {
								for (bx = 0; bx < bC; bx++) {
									E(bv[bx])
								}
							} else {
								E(bv)
							}
						}
						if (bv.nodeType) {
							bE.push(bv)
						} else {
							bE = b.merge(bE, bv)
						}
					}
					if (bD) {
						bB = function(bH) {
							return !bH.type || bi.test(bH.type)
						};
						for (bA = 0; bE[bA]; bA++) {
							if (bw
									&& b.nodeName(bE[bA], "script")
									&& (!bE[bA].type || bE[bA].type
											.toLowerCase() === "text/javascript")) {
								bw.push(bE[bA].parentNode ? bE[bA].parentNode
										.removeChild(bE[bA]) : bE[bA])
							} else {
								if (bE[bA].nodeType === 1) {
									var bF = b
											.grep(
													bE[bA]
															.getElementsByTagName("script"),
													bB);
									bE.splice.apply(bE, [ bA + 1, 0 ]
											.concat(bF))
								}
								bD.appendChild(bE[bA])
							}
						}
					}
					return bE
				},
				cleanData : function(br) {
					var bu, bs, e = b.cache, bz = b.expando, bx = b.event.special, bw = b.support.deleteExpando;
					for ( var bv = 0, bt; (bt = br[bv]) != null; bv++) {
						if (bt.nodeName && b.noData[bt.nodeName.toLowerCase()]) {
							continue
						}
						bs = bt[b.expando];
						if (bs) {
							bu = e[bs] && e[bs][bz];
							if (bu && bu.events) {
								for ( var by in bu.events) {
									if (bx[by]) {
										b.event.remove(bt, by)
									} else {
										b.removeEvent(bt, by, bu.handle)
									}
								}
								if (bu.handle) {
									bu.handle.elem = null
								}
							}
							if (bw) {
								delete bt[b.expando]
							} else {
								if (bt.removeAttribute) {
									bt.removeAttribute(b.expando)
								}
							}
							delete e[bs]
						}
					}
				}
			});
	function bl(e, br) {
		if (br.src) {
			b.ajax( {
				url : br.src,
				async : false,
				dataType : "script"
			})
		} else {
			b.globalEval((br.text || br.textContent || br.innerHTML || "")
					.replace(aI, "/*$0*/"))
		}
		if (br.parentNode) {
			br.parentNode.removeChild(br)
		}
	}
	var af = /alpha\([^)]*\)/i, ao = /opacity=([^)]*)/, z = /([A-Z]|^ms)/g, a8 = /^-?\d+(?:px)?$/i, bk = /^-?\d/, I = /^([\-+])=([\-+.\de]+)/, a3 = {
		position : "absolute",
		visibility : "hidden",
		display : "block"
	}, ai = [ "Left", "Right" ], aY = [ "Top", "Bottom" ], V, aD, aS;
	b.fn.css = function(e, br) {
		if (arguments.length === 2 && br === K) {
			return this
		}
		return b.access(this, e, br, true, function(bt, bs, bu) {
			return bu !== K ? b.style(bt, bs, bu) : b.css(bt, bs)
		})
	};
	b
			.extend( {
				cssHooks : {
					opacity : {
						get : function(bs, br) {
							if (br) {
								var e = V(bs, "opacity", "opacity");
								return e === "" ? "1" : e
							} else {
								return bs.style.opacity
							}
						}
					}
				},
				cssNumber : {
					fillOpacity : true,
					fontWeight : true,
					lineHeight : true,
					opacity : true,
					orphans : true,
					widows : true,
					zIndex : true,
					zoom : true
				},
				cssProps : {
					"float" : b.support.cssFloat ? "cssFloat" : "styleFloat"
				},
				style : function(bt, bs, bz, bu) {
					if (!bt || bt.nodeType === 3 || bt.nodeType === 8
							|| !bt.style) {
						return
					}
					var bx, by, bv = b.camelCase(bs), br = bt.style, bA = b.cssHooks[bv];
					bs = b.cssProps[bv] || bv;
					if (bz !== K) {
						by = typeof bz;
						if (by === "string" && (bx = I.exec(bz))) {
							bz = (+(bx[1] + 1) * +bx[2])
									+ parseFloat(b.css(bt, bs));
							by = "number"
						}
						if (bz == null || by === "number" && isNaN(bz)) {
							return
						}
						if (by === "number" && !b.cssNumber[bv]) {
							bz += "px"
						}
						if (!bA || !("set" in bA)
								|| (bz = bA.set(bt, bz)) !== K) {
							try {
								br[bs] = bz
							} catch (bw) {
							}
						}
					} else {
						if (bA && "get" in bA
								&& (bx = bA.get(bt, false, bu)) !== K) {
							return bx
						}
						return br[bs]
					}
				},
				css : function(bu, bt, br) {
					var bs, e;
					bt = b.camelCase(bt);
					e = b.cssHooks[bt];
					bt = b.cssProps[bt] || bt;
					if (bt === "cssFloat") {
						bt = "float"
					}
					if (e && "get" in e && (bs = e.get(bu, true, br)) !== K) {
						return bs
					} else {
						if (V) {
							return V(bu, bt)
						}
					}
				},
				swap : function(bt, bs, bu) {
					var e = {};
					for ( var br in bs) {
						e[br] = bt.style[br];
						bt.style[br] = bs[br]
					}
					bu.call(bt);
					for (br in bs) {
						bt.style[br] = e[br]
					}
				}
			});
	b.curCSS = b.css;
	b.each( [ "height", "width" ], function(br, e) {
		b.cssHooks[e] = {
			get : function(bu, bt, bs) {
				var bv;
				if (bt) {
					if (bu.offsetWidth !== 0) {
						return o(bu, e, bs)
					} else {
						b.swap(bu, a3, function() {
							bv = o(bu, e, bs)
						})
					}
					return bv
				}
			},
			set : function(bs, bt) {
				if (a8.test(bt)) {
					bt = parseFloat(bt);
					if (bt >= 0) {
						return bt + "px"
					}
				} else {
					return bt
				}
			}
		}
	});
	if (!b.support.opacity) {
		b.cssHooks.opacity = {
			get : function(br, e) {
				return ao.test((e && br.currentStyle ? br.currentStyle.filter
						: br.style.filter)
						|| "") ? (parseFloat(RegExp.$1) / 100) + "" : e ? "1"
						: ""
			},
			set : function(bu, bv) {
				var bt = bu.style, br = bu.currentStyle, e = b.isNaN(bv) ? ""
						: "alpha(opacity=" + bv * 100 + ")", bs = br
						&& br.filter || bt.filter || "";
				bt.zoom = 1;
				if (bv >= 1 && b.trim(bs.replace(af, "")) === "") {
					bt.removeAttribute("filter");
					if (br && !br.filter) {
						return
					}
				}
				bt.filter = af.test(bs) ? bs.replace(af, e) : bs + " " + e
			}
		}
	}
	b(function() {
		if (!b.support.reliableMarginRight) {
			b.cssHooks.marginRight = {
				get : function(bs, br) {
					var e;
					b.swap(bs, {
						display : "inline-block"
					}, function() {
						if (br) {
							e = V(bs, "margin-right", "marginRight")
						} else {
							e = bs.style.marginRight
						}
					});
					return e
				}
			}
		}
	});
	if (ap.defaultView && ap.defaultView.getComputedStyle) {
		aD = function(bu, bs) {
			var br, bt, e;
			bs = bs.replace(z, "-$1").toLowerCase();
			if (!(bt = bu.ownerDocument.defaultView)) {
				return K
			}
			if ((e = bt.getComputedStyle(bu, null))) {
				br = e.getPropertyValue(bs);
				if (br === ""
						&& !b.contains(bu.ownerDocument.documentElement, bu)) {
					br = b.style(bu, bs)
				}
			}
			return br
		}
	}
	if (ap.documentElement.currentStyle) {
		aS = function(bu, bs) {
			var bv, br = bu.currentStyle && bu.currentStyle[bs], e = bu.runtimeStyle
					&& bu.runtimeStyle[bs], bt = bu.style;
			if (!a8.test(br) && bk.test(br)) {
				bv = bt.left;
				if (e) {
					bu.runtimeStyle.left = bu.currentStyle.left
				}
				bt.left = bs === "fontSize" ? "1em" : (br || 0);
				br = bt.pixelLeft + "px";
				bt.left = bv;
				if (e) {
					bu.runtimeStyle.left = e
				}
			}
			return br === "" ? "auto" : br
		}
	}
	V = aD || aS;
	function o(bs, br, e) {
		var bu = br === "width" ? bs.offsetWidth : bs.offsetHeight, bt = br === "width" ? ai
				: aY;
		if (bu > 0) {
			if (e !== "border") {
				b
						.each(
								bt,
								function() {
									if (!e) {
										bu -= parseFloat(b.css(bs, "padding"
												+ this)) || 0
									}
									if (e === "margin") {
										bu += parseFloat(b.css(bs, e + this)) || 0
									} else {
										bu -= parseFloat(b.css(bs, "border"
												+ this + "Width")) || 0
									}
								})
			}
			return bu + "px"
		}
		bu = V(bs, br, br);
		if (bu < 0 || bu == null) {
			bu = bs.style[br] || 0
		}
		bu = parseFloat(bu) || 0;
		if (e) {
			b.each(bt, function() {
				bu += parseFloat(b.css(bs, "padding" + this)) || 0;
				if (e !== "padding") {
					bu += parseFloat(b.css(bs, "border" + this + "Width")) || 0
				}
				if (e === "margin") {
					bu += parseFloat(b.css(bs, e + this)) || 0
				}
			})
		}
		return bu + "px"
	}
	if (b.expr && b.expr.filters) {
		b.expr.filters.hidden = function(bs) {
			var br = bs.offsetWidth, e = bs.offsetHeight;
			return (br === 0 && e === 0)
					|| (!b.support.reliableHiddenOffsets && (bs.style.display || b
							.css(bs, "display")) === "none")
		};
		b.expr.filters.visible = function(e) {
			return !b.expr.filters.hidden(e)
		}
	}
	var j = /%20/g, ak = /\[\]$/, bp = /\r?\n/g, bn = /#.*$/, ay = /^(.*?):[ \t]*([^\r\n]*)\r?$/mg, aV = /^(?:color|date|datetime|datetime-local|email|hidden|month|number|password|range|search|tel|text|time|url|week)$/i, aH = /^(?:about|app|app\-storage|.+\-extension|file|res|widget):$/, aK = /^(?:GET|HEAD)$/, c = /^\/\//, L = /\?/, a2 = /<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi, q = /^(?:select|textarea)/i, h = /\s+/, bo = /([?&])_=[^&]*/, J = /^([\w\+\.\-]+:)(?:\/\/([^\/?#:]*)(?::(\d+))?)?/, A = b.fn.load, W = {}, r = {}, az, s, aO = [ "*/" ]
			+ [ "*" ];
	try {
		az = bh.href
	} catch (aq) {
		az = ap.createElement("a");
		az.href = "";
		az = az.href
	}
	s = J.exec(az.toLowerCase()) || [];
	function f(e) {
		return function(bu, bw) {
			if (typeof bu !== "string") {
				bw = bu;
				bu = "*"
			}
			if (b.isFunction(bw)) {
				var bt = bu.toLowerCase().split(h), bs = 0, bv = bt.length, br, bx, by;
				for (; bs < bv; bs++) {
					br = bt[bs];
					by = /^\+/.test(br);
					if (by) {
						br = br.substr(1) || "*"
					}
					bx = e[br] = e[br] || [];
					bx[by ? "unshift" : "push"](bw)
				}
			}
		}
	}
	function aQ(br, bA, bv, bz, bx, bt) {
		bx = bx || bA.dataTypes[0];
		bt = bt || {};
		bt[bx] = true;
		var bw = br[bx], bs = 0, e = bw ? bw.length : 0, bu = (br === W), by;
		for (; bs < e && (bu || !by); bs++) {
			by = bw[bs](bA, bv, bz);
			if (typeof by === "string") {
				if (!bu || bt[by]) {
					by = K
				} else {
					bA.dataTypes.unshift(by);
					by = aQ(br, bA, bv, bz, by, bt)
				}
			}
		}
		if ((bu || !by) && !bt["*"]) {
			by = aQ(br, bA, bv, bz, "*", bt)
		}
		return by
	}
	function ah(bs, bt) {
		var br, e, bu = b.ajaxSettings.flatOptions || {};
		for (br in bt) {
			if (bt[br] !== K) {
				(bu[br] ? bs : (e || (e = {})))[br] = bt[br]
			}
		}
		if (e) {
			b.extend(true, bs, e)
		}
	}
	b.fn.extend( {
		load : function(bs, bv, bw) {
			if (typeof bs !== "string" && A) {
				return A.apply(this, arguments)
			} else {
				if (!this.length) {
					return this
				}
			}
			var bu = bs.indexOf(" ");
			if (bu >= 0) {
				var e = bs.slice(bu, bs.length);
				bs = bs.slice(0, bu)
			}
			var bt = "GET";
			if (bv) {
				if (b.isFunction(bv)) {
					bw = bv;
					bv = K
				} else {
					if (typeof bv === "object") {
						bv = b.param(bv, b.ajaxSettings.traditional);
						bt = "POST"
					}
				}
			}
			var br = this;
			b.ajax( {
				url : bs,
				type : bt,
				dataType : "html",
				data : bv,
				complete : function(by, bx, bz) {
					bz = by.responseText;
					if (by.isResolved()) {
						by.done(function(bA) {
							bz = bA
						});
						br.html(e ? b("<div>").append(bz.replace(a2, "")).find(
								e) : bz)
					}
					if (bw) {
						br.each(bw, [ bz, bx, by ])
					}
				}
			});
			return this
		},
		serialize : function() {
			return b.param(this.serializeArray())
		},
		serializeArray : function() {
			return this.map(function() {
				return this.elements ? b.makeArray(this.elements) : this
			}).filter(
					function() {
						return this.name
								&& !this.disabled
								&& (this.checked || q.test(this.nodeName) || aV
										.test(this.type))
					}).map(
					function(e, br) {
						var bs = b(this).val();
						return bs == null ? null : b.isArray(bs) ? b.map(bs,
								function(bu, bt) {
									return {
										name : br.name,
										value : bu.replace(bp, "\r\n")
									}
								}) : {
							name : br.name,
							value : bs.replace(bp, "\r\n")
						}
					}).get()
		}
	});
	b.each("ajaxStart ajaxStop ajaxComplete ajaxError ajaxSuccess ajaxSend"
			.split(" "), function(e, br) {
		b.fn[br] = function(bs) {
			return this.bind(br, bs)
		}
	});
	b.each( [ "get", "post" ], function(e, br) {
		b[br] = function(bs, bu, bv, bt) {
			if (b.isFunction(bu)) {
				bt = bt || bv;
				bv = bu;
				bu = K
			}
			return b.ajax( {
				type : br,
				url : bs,
				data : bu,
				success : bv,
				dataType : bt
			})
		}
	});
	b
			.extend( {
				getScript : function(e, br) {
					return b.get(e, K, br, "script")
				},
				getJSON : function(e, br, bs) {
					return b.get(e, br, bs, "json")
				},
				ajaxSetup : function(br, e) {
					if (e) {
						ah(br, b.ajaxSettings)
					} else {
						e = br;
						br = b.ajaxSettings
					}
					ah(br, e);
					return br
				},
				ajaxSettings : {
					url : az,
					isLocal : aH.test(s[1]),
					global : true,
					type : "GET",
					contentType : "application/x-www-form-urlencoded;charset=UTF-8",
					processData : true,
					async : true,
					accepts : {
						xml : "application/xml, text/xml",
						html : "text/html",
						text : "text/plain",
						json : "application/json, text/javascript",
						"*" : aO
					},
					contents : {
						xml : /xml/,
						html : /html/,
						json : /json/
					},
					responseFields : {
						xml : "responseXML",
						text : "responseText"
					},
					converters : {
						"* text" : a7.String,
						"text html" : true,
						"text json" : b.parseJSON,
						"text xml" : b.parseXML
					},
					flatOptions : {
						context : true,
						url : true
					}
				},
				ajaxPrefilter : f(W),
				ajaxTransport : f(r),
				ajax : function(bv, bt) {
					if (typeof bv === "object") {
						bt = bv;
						bv = K
					}
					bt = bt || {};
					var bz = b.ajaxSetup( {}, bt), bO = bz.context || bz, bC = bO !== bz
							&& (bO.nodeType || bO instanceof b) ? b(bO)
							: b.event, bN = b.Deferred(), bJ = b._Deferred(), bx = bz.statusCode
							|| {}, by, bD = {}, bK = {}, bM, bu, bH, bA, bE, bw = 0, bs, bG, bF = {
						readyState : 0,
						setRequestHeader : function(bP, bQ) {
							if (!bw) {
								var e = bP.toLowerCase();
								bP = bK[e] = bK[e] || bP;
								bD[bP] = bQ
							}
							return this
						},
						getAllResponseHeaders : function() {
							return bw === 2 ? bM : null
						},
						getResponseHeader : function(bP) {
							var e;
							if (bw === 2) {
								if (!bu) {
									bu = {};
									while ((e = ay.exec(bM))) {
										bu[e[1].toLowerCase()] = e[2]
									}
								}
								e = bu[bP.toLowerCase()]
							}
							return e === K ? null : e
						},
						overrideMimeType : function(e) {
							if (!bw) {
								bz.mimeType = e
							}
							return this
						},
						abort : function(e) {
							e = e || "abort";
							if (bH) {
								bH.abort(e)
							}
							bB(0, e);
							return this
						}
					};
					function bB(bV, bQ, bW, bS) {
						if (bw === 2) {
							return
						}
						bw = 2;
						if (bA) {
							clearTimeout(bA)
						}
						bH = K;
						bM = bS || "";
						bF.readyState = bV > 0 ? 4 : 0;
						var bP, b0, bZ, bT = bQ, bU = bW ? bf(bz, bF, bW) : K, bR, bY;
						if (bV >= 200 && bV < 300 || bV === 304) {
							if (bz.ifModified) {
								if ((bR = bF.getResponseHeader("Last-Modified"))) {
									b.lastModified[by] = bR
								}
								if ((bY = bF.getResponseHeader("Etag"))) {
									b.etag[by] = bY
								}
							}
							if (bV === 304) {
								bT = "notmodified";
								bP = true
							} else {
								try {
									b0 = F(bz, bU);
									bT = "success";
									bP = true
								} catch (bX) {
									bT = "parsererror";
									bZ = bX
								}
							}
						} else {
							bZ = bT;
							if (!bT || bV) {
								bT = "error";
								if (bV < 0) {
									bV = 0
								}
							}
						}
						bF.status = bV;
						bF.statusText = "" + (bQ || bT);
						if (bP) {
							bN.resolveWith(bO, [ b0, bT, bF ])
						} else {
							bN.rejectWith(bO, [ bF, bT, bZ ])
						}
						bF.statusCode(bx);
						bx = K;
						if (bs) {
							bC.trigger("ajax" + (bP ? "Success" : "Error"), [
									bF, bz, bP ? b0 : bZ ])
						}
						bJ.resolveWith(bO, [ bF, bT ]);
						if (bs) {
							bC.trigger("ajaxComplete", [ bF, bz ]);
							if (!(--b.active)) {
								b.event.trigger("ajaxStop")
							}
						}
					}
					bN.promise(bF);
					bF.success = bF.done;
					bF.error = bF.fail;
					bF.complete = bJ.done;
					bF.statusCode = function(bP) {
						if (bP) {
							var e;
							if (bw < 2) {
								for (e in bP) {
									bx[e] = [ bx[e], bP[e] ]
								}
							} else {
								e = bP[bF.status];
								bF.then(e, e)
							}
						}
						return this
					};
					bz.url = ((bv || bz.url) + "").replace(bn, "").replace(c,
							s[1] + "//");
					bz.dataTypes = b.trim(bz.dataType || "*").toLowerCase()
							.split(h);
					if (bz.crossDomain == null) {
						bE = J.exec(bz.url.toLowerCase());
						bz.crossDomain = !!(bE && (bE[1] != s[1]
								|| bE[2] != s[2] || (bE[3] || (bE[1] === "http:" ? 80
								: 443)) != (s[3] || (s[1] === "http:" ? 80
								: 443))))
					}
					if (bz.data && bz.processData
							&& typeof bz.data !== "string") {
						bz.data = b.param(bz.data, bz.traditional)
					}
					aQ(W, bz, bt, bF);
					if (bw === 2) {
						return false
					}
					bs = bz.global;
					bz.type = bz.type.toUpperCase();
					bz.hasContent = !aK.test(bz.type);
					if (bs && b.active++ === 0) {
						b.event.trigger("ajaxStart")
					}
					if (!bz.hasContent) {
						if (bz.data) {
							bz.url += (L.test(bz.url) ? "&" : "?") + bz.data;
							delete bz.data
						}
						by = bz.url;
						if (bz.cache === false) {
							var br = b.now(), bL = bz.url.replace(bo, "$1_="
									+ br);
							bz.url = bL
									+ ((bL === bz.url) ? (L.test(bz.url) ? "&"
											: "?")
											+ "_=" + br : "")
						}
					}
					if (bz.data && bz.hasContent && bz.contentType !== false
							|| bt.contentType) {
						bF.setRequestHeader("Content-Type", bz.contentType)
					}
					if (bz.ifModified) {
						by = by || bz.url;
						if (b.lastModified[by]) {
							bF.setRequestHeader("If-Modified-Since",
									b.lastModified[by])
						}
						if (b.etag[by]) {
							bF.setRequestHeader("If-None-Match", b.etag[by])
						}
					}
					bF
							.setRequestHeader(
									"Accept",
									bz.dataTypes[0]
											&& bz.accepts[bz.dataTypes[0]] ? bz.accepts[bz.dataTypes[0]]
											+ (bz.dataTypes[0] !== "*" ? ", "
													+ aO + "; q=0.01" : "")
											: bz.accepts["*"]);
					for (bG in bz.headers) {
						bF.setRequestHeader(bG, bz.headers[bG])
					}
					if (bz.beforeSend
							&& (bz.beforeSend.call(bO, bF, bz) === false || bw === 2)) {
						bF.abort();
						return false
					}
					for (bG in {
						success : 1,
						error : 1,
						complete : 1
					}) {
						bF[bG](bz[bG])
					}
					bH = aQ(r, bz, bt, bF);
					if (!bH) {
						bB(-1, "No Transport")
					} else {
						bF.readyState = 1;
						if (bs) {
							bC.trigger("ajaxSend", [ bF, bz ])
						}
						if (bz.async && bz.timeout > 0) {
							bA = setTimeout(function() {
								bF.abort("timeout")
							}, bz.timeout)
						}
						try {
							bw = 1;
							bH.send(bD, bB)
						} catch (bI) {
							if (bw < 2) {
								bB(-1, bI)
							} else {
								b.error(bI)
							}
						}
					}
					return bF
				},
				param : function(e, bs) {
					var br = [], bu = function(bv, bw) {
						bw = b.isFunction(bw) ? bw() : bw;
						br[br.length] = encodeURIComponent(bv) + "="
								+ encodeURIComponent(bw)
					};
					if (bs === K) {
						bs = b.ajaxSettings.traditional
					}
					if (b.isArray(e) || (e.jquery && !b.isPlainObject(e))) {
						b.each(e, function() {
							bu(this.name, this.value)
						})
					} else {
						for ( var bt in e) {
							v(bt, e[bt], bs, bu)
						}
					}
					return br.join("&").replace(j, "+")
				}
			});
	function v(bs, bu, br, bt) {
		if (b.isArray(bu)) {
			b.each(bu, function(bw, bv) {
				if (br || ak.test(bs)) {
					bt(bs, bv)
				} else {
					v(bs
							+ "["
							+ (typeof bv === "object" || b.isArray(bv) ? bw
									: "") + "]", bv, br, bt)
				}
			})
		} else {
			if (!br && bu != null && typeof bu === "object") {
				for ( var e in bu) {
					v(bs + "[" + e + "]", bu[e], br, bt)
				}
			} else {
				bt(bs, bu)
			}
		}
	}
	b.extend( {
		active : 0,
		lastModified : {},
		etag : {}
	});
	function bf(bz, by, bv) {
		var br = bz.contents, bx = bz.dataTypes, bs = bz.responseFields, bu, bw, bt, e;
		for (bw in bs) {
			if (bw in bv) {
				by[bs[bw]] = bv[bw]
			}
		}
		while (bx[0] === "*") {
			bx.shift();
			if (bu === K) {
				bu = bz.mimeType || by.getResponseHeader("content-type")
			}
		}
		if (bu) {
			for (bw in br) {
				if (br[bw] && br[bw].test(bu)) {
					bx.unshift(bw);
					break
				}
			}
		}
		if (bx[0] in bv) {
			bt = bx[0]
		} else {
			for (bw in bv) {
				if (!bx[0] || bz.converters[bw + " " + bx[0]]) {
					bt = bw;
					break
				}
				if (!e) {
					e = bw
				}
			}
			bt = bt || e
		}
		if (bt) {
			if (bt !== bx[0]) {
				bx.unshift(bt)
			}
			return bv[bt]
		}
	}
	function F(bD, bv) {
		if (bD.dataFilter) {
			bv = bD.dataFilter(bv, bD.dataType)
		}
		var bz = bD.dataTypes, bC = {}, bw, bA, bs = bz.length, bx, by = bz[0], bt, bu, bB, br, e;
		for (bw = 1; bw < bs; bw++) {
			if (bw === 1) {
				for (bA in bD.converters) {
					if (typeof bA === "string") {
						bC[bA.toLowerCase()] = bD.converters[bA]
					}
				}
			}
			bt = by;
			by = bz[bw];
			if (by === "*") {
				by = bt
			} else {
				if (bt !== "*" && bt !== by) {
					bu = bt + " " + by;
					bB = bC[bu] || bC["* " + by];
					if (!bB) {
						e = K;
						for (br in bC) {
							bx = br.split(" ");
							if (bx[0] === bt || bx[0] === "*") {
								e = bC[bx[1] + " " + by];
								if (e) {
									br = bC[br];
									if (br === true) {
										bB = e
									} else {
										if (e === true) {
											bB = br
										}
									}
									break
								}
							}
						}
					}
					if (!(bB || e)) {
						b
								.error("No conversion from "
										+ bu.replace(" ", " to "))
					}
					if (bB !== true) {
						bv = bB ? bB(bv) : e(br(bv))
					}
				}
			}
		}
		return bv
	}
	var ax = b.now(), u = /(\=)\?(&|$)|\?\?/i;
	b.ajaxSetup( {
		jsonp : "callback",
		jsonpCallback : function() {
			return b.expando + "_" + (ax++)
		}
	});
	b
			.ajaxPrefilter(
					"json jsonp",
					function(bz, bw, by) {
						var bt = bz.contentType === "application/x-www-form-urlencoded;charset=UTF-8"
								&& (typeof bz.data === "string");
						if (bz.dataTypes[0] === "jsonp" || bz.jsonp !== false
								&& (u.test(bz.url) || bt && u.test(bz.data))) {
							var bx, bs = bz.jsonpCallback = b
									.isFunction(bz.jsonpCallback) ? bz
									.jsonpCallback() : bz.jsonpCallback, bv = a7[bs], e = bz.url, bu = bz.data, br = "$1"
									+ bs + "$2";
							if (bz.jsonp !== false) {
								e = e.replace(u, br);
								if (bz.url === e) {
									if (bt) {
										bu = bu.replace(u, br)
									}
									if (bz.data === bu) {
										e += (/\?/.test(e) ? "&" : "?")
												+ bz.jsonp + "=" + bs
									}
								}
							}
							bz.url = e;
							bz.data = bu;
							a7[bs] = function(bA) {
								bx = [ bA ]
							};
							by.always(function() {
								a7[bs] = bv;
								if (bx && b.isFunction(bv)) {
									a7[bs](bx[0])
								}
							});
							bz.converters["script json"] = function() {
								if (!bx) {
									b.error(bs + " was not called")
								}
								return bx[0]
							};
							bz.dataTypes[0] = "json";
							return "script"
						}
					});
	b
			.ajaxSetup( {
				accepts : {
					script : "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript"
				},
				contents : {
					script : /javascript|ecmascript/
				},
				converters : {
					"text script" : function(e) {
						b.globalEval(e);
						return e
					}
				}
			});
	b.ajaxPrefilter("script", function(e) {
		if (e.cache === K) {
			e.cache = false
		}
		if (e.crossDomain) {
			e.type = "GET";
			e.global = false
		}
	});
	b.ajaxTransport("script", function(bs) {
		if (bs.crossDomain) {
			var e, br = ap.head || ap.getElementsByTagName("head")[0]
					|| ap.documentElement;
			return {
				send : function(bt, bu) {
					e = ap.createElement("script");
					e.async = "async";
					if (bs.scriptCharset) {
						e.charset = bs.scriptCharset
					}
					e.src = bs.url;
					e.onload = e.onreadystatechange = function(bw, bv) {
						if (bv || !e.readyState
								|| /loaded|complete/.test(e.readyState)) {
							e.onload = e.onreadystatechange = null;
							if (br && e.parentNode) {
								br.removeChild(e)
							}
							e = K;
							if (!bv) {
								bu(200, "success")
							}
						}
					};
					br.insertBefore(e, br.firstChild)
				},
				abort : function() {
					if (e) {
						e.onload(0, 1)
					}
				}
			}
		}
	});
	var B = a7.ActiveXObject ? function() {
		for ( var e in M) {
			M[e](0, 1)
		}
	} : false, y = 0, M;
	function aG() {
		try {
			return new a7.XMLHttpRequest()
		} catch (br) {
		}
	}
	function ae() {
		try {
			return new a7.ActiveXObject("Microsoft.XMLHTTP")
		} catch (br) {
		}
	}
	b.ajaxSettings.xhr = a7.ActiveXObject ? function() {
		return !this.isLocal && aG() || ae()
	} : aG;
	(function(e) {
		b.extend(b.support, {
			ajax : !!e,
			cors : !!e && ("withCredentials" in e)
		})
	})(b.ajaxSettings.xhr());
	if (b.support.ajax) {
		b
				.ajaxTransport(function(e) {
					if (!e.crossDomain || b.support.cors) {
						var br;
						return {
							send : function(bx, bs) {
								var bw = e.xhr(), bv, bu;
								if (e.username) {
									bw.open(e.type, e.url, e.async, e.username,
											e.password)
								} else {
									bw.open(e.type, e.url, e.async)
								}
								if (e.xhrFields) {
									for (bu in e.xhrFields) {
										bw[bu] = e.xhrFields[bu]
									}
								}
								if (e.mimeType && bw.overrideMimeType) {
									bw.overrideMimeType(e.mimeType)
								}
								if (!e.crossDomain && !bx["X-Requested-With"]) {
									bx["X-Requested-With"] = "XMLHttpRequest"
								}
								try {
									for (bu in bx) {
										bw.setRequestHeader(bu, bx[bu])
									}
								} catch (bt) {
								}
								bw.send((e.hasContent && e.data) || null);
								br = function(bG, bA) {
									var bB, bz, by, bE, bD;
									try {
										if (br && (bA || bw.readyState === 4)) {
											br = K;
											if (bv) {
												bw.onreadystatechange = b.noop;
												if (B) {
													delete M[bv]
												}
											}
											if (bA) {
												if (bw.readyState !== 4) {
													bw.abort()
												}
											} else {
												bB = bw.status;
												by = bw.getAllResponseHeaders();
												bE = {};
												bD = bw.responseXML;
												if (bD && bD.documentElement) {
													bE.xml = bD
												}
												bE.text = bw.responseText;
												try {
													bz = bw.statusText
												} catch (bF) {
													bz = ""
												}
												if (!bB && e.isLocal
														&& !e.crossDomain) {
													bB = bE.text ? 200 : 404
												} else {
													if (bB === 1223) {
														bB = 204
													}
												}
											}
										}
									} catch (bC) {
										if (!bA) {
											bs(-1, bC)
										}
									}
									if (bE) {
										bs(bB, bz, bE, by)
									}
								};
								if (!e.async || bw.readyState === 4) {
									br()
								} else {
									bv = ++y;
									if (B) {
										if (!M) {
											M = {};
											b(a7).unload(B)
										}
										M[bv] = br
									}
									bw.onreadystatechange = br
								}
							},
							abort : function() {
								if (br) {
									br(0, 1)
								}
							}
						}
					}
				})
	}
	var Q = {}, a4, m, aw = /^(?:toggle|show|hide)$/, aM = /^([+\-]=)?([\d+.\-]+)([a-z%]*)$/i, aZ, aC = [
			[ "height", "marginTop", "marginBottom", "paddingTop",
					"paddingBottom" ],
			[ "width", "marginLeft", "marginRight", "paddingLeft",
					"paddingRight" ], [ "opacity" ] ], a0;
	b.fn
			.extend( {
				show : function(bt, bw, bv) {
					var bs, bu;
					if (bt || bt === 0) {
						return this.animate(aX("show", 3), bt, bw, bv)
					} else {
						for ( var br = 0, e = this.length; br < e; br++) {
							bs = this[br];
							if (bs.style) {
								bu = bs.style.display;
								if (!b._data(bs, "olddisplay") && bu === "none") {
									bu = bs.style.display = ""
								}
								if (bu === ""
										&& b.css(bs, "display") === "none") {
									b._data(bs, "olddisplay", x(bs.nodeName))
								}
							}
						}
						for (br = 0; br < e; br++) {
							bs = this[br];
							if (bs.style) {
								bu = bs.style.display;
								if (bu === "" || bu === "none") {
									bs.style.display = b
											._data(bs, "olddisplay")
											|| ""
								}
							}
						}
						return this
					}
				},
				hide : function(bs, bv, bu) {
					if (bs || bs === 0) {
						return this.animate(aX("hide", 3), bs, bv, bu)
					} else {
						for ( var br = 0, e = this.length; br < e; br++) {
							if (this[br].style) {
								var bt = b.css(this[br], "display");
								if (bt !== "none"
										&& !b._data(this[br], "olddisplay")) {
									b._data(this[br], "olddisplay", bt)
								}
							}
						}
						for (br = 0; br < e; br++) {
							if (this[br].style) {
								this[br].style.display = "none"
							}
						}
						return this
					}
				},
				_toggle : b.fn.toggle,
				toggle : function(bs, br, bt) {
					var e = typeof bs === "boolean";
					if (b.isFunction(bs) && b.isFunction(br)) {
						this._toggle.apply(this, arguments)
					} else {
						if (bs == null || e) {
							this.each(function() {
								var bu = e ? bs : b(this).is(":hidden");
								b(this)[bu ? "show" : "hide"]()
							})
						} else {
							this.animate(aX("toggle", 3), bs, br, bt)
						}
					}
					return this
				},
				fadeTo : function(e, bt, bs, br) {
					return this.filter(":hidden").css("opacity", 0).show()
							.end().animate( {
								opacity : bt
							}, e, bs, br)
				},
				animate : function(bu, br, bt, bs) {
					var e = b.speed(br, bt, bs);
					if (b.isEmptyObject(bu)) {
						return this.each(e.complete, [ false ])
					}
					bu = b.extend( {}, bu);
					return this[e.queue === false ? "each" : "queue"]
							(function() {
								if (e.queue === false) {
									b._mark(this)
								}
								var by = b.extend( {}, e), bF = this.nodeType === 1, bC = bF
										&& b(this).is(":hidden"), bv, bz, bx, bE, bD, bB, bw, bA, bG;
								by.animatedProperties = {};
								for (bx in bu) {
									bv = b.camelCase(bx);
									if (bx !== bv) {
										bu[bv] = bu[bx];
										delete bu[bx]
									}
									bz = bu[bv];
									if (b.isArray(bz)) {
										by.animatedProperties[bv] = bz[1];
										bz = bu[bv] = bz[0]
									} else {
										by.animatedProperties[bv] = by.specialEasing
												&& by.specialEasing[bv]
												|| by.easing || "swing"
									}
									if (bz === "hide" && bC || bz === "show"
											&& !bC) {
										return by.complete.call(this)
									}
									if (bF
											&& (bv === "height" || bv === "width")) {
										by.overflow = [ this.style.overflow,
												this.style.overflowX,
												this.style.overflowY ];
										if (b.css(this, "display") === "inline"
												&& b.css(this, "float") === "none") {
											if (!b.support.inlineBlockNeedsLayout) {
												this.style.display = "inline-block"
											} else {
												bE = x(this.nodeName);
												if (bE === "inline") {
													this.style.display = "inline-block"
												} else {
													this.style.display = "inline";
													this.style.zoom = 1
												}
											}
										}
									}
								}
								if (by.overflow != null) {
									this.style.overflow = "hidden"
								}
								for (bx in bu) {
									bD = new b.fx(this, by, bx);
									bz = bu[bx];
									if (aw.test(bz)) {
										bD[bz === "toggle" ? bC ? "show"
												: "hide" : bz]()
									} else {
										bB = aM.exec(bz);
										bw = bD.cur();
										if (bB) {
											bA = parseFloat(bB[2]);
											bG = bB[3]
													|| (b.cssNumber[bx] ? ""
															: "px");
											if (bG !== "px") {
												b.style(this, bx, (bA || 1)
														+ bG);
												bw = ((bA || 1) / bD.cur())
														* bw;
												b.style(this, bx, bw + bG)
											}
											if (bB[1]) {
												bA = ((bB[1] === "-=" ? -1 : 1) * bA)
														+ bw
											}
											bD.custom(bw, bA, bG)
										} else {
											bD.custom(bw, bz, "")
										}
									}
								}
								return true
							})
				},
				stop : function(br, e) {
					if (br) {
						this.queue( [])
					}
					this.each(function() {
						var bt = b.timers, bs = bt.length;
						if (!e) {
							b._unmark(true, this)
						}
						while (bs--) {
							if (bt[bs].elem === this) {
								if (e) {
									bt[bs](true)
								}
								bt.splice(bs, 1)
							}
						}
					});
					if (!e) {
						this.dequeue()
					}
					return this
				}
			});
	function bc() {
		setTimeout(an, 0);
		return (a0 = b.now())
	}
	function an() {
		a0 = K
	}
	function aX(br, e) {
		var bs = {};
		b.each(aC.concat.apply( [], aC.slice(0, e)), function() {
			bs[this] = br
		});
		return bs
	}
	b.each( {
		slideDown : aX("show", 1),
		slideUp : aX("hide", 1),
		slideToggle : aX("toggle", 1),
		fadeIn : {
			opacity : "show"
		},
		fadeOut : {
			opacity : "hide"
		},
		fadeToggle : {
			opacity : "toggle"
		}
	}, function(e, br) {
		b.fn[e] = function(bs, bu, bt) {
			return this.animate(br, bs, bu, bt)
		}
	});
	b
			.extend( {
				speed : function(bs, bt, br) {
					var e = bs && typeof bs === "object" ? b.extend( {}, bs)
							: {
								complete : br || !br && bt || b.isFunction(bs)
										&& bs,
								duration : bs,
								easing : br && bt || bt && !b.isFunction(bt)
										&& bt
							};
					e.duration = b.fx.off ? 0
							: typeof e.duration === "number" ? e.duration
									: e.duration in b.fx.speeds ? b.fx.speeds[e.duration]
											: b.fx.speeds._default;
					e.old = e.complete;
					e.complete = function(bu) {
						if (b.isFunction(e.old)) {
							e.old.call(this)
						}
						if (e.queue !== false) {
							b.dequeue(this)
						} else {
							if (bu !== false) {
								b._unmark(this)
							}
						}
					};
					return e
				},
				easing : {
					linear : function(bs, bt, e, br) {
						return e + br * bs
					},
					swing : function(bs, bt, e, br) {
						return ((-Math.cos(bs * Math.PI) / 2) + 0.5) * br + e
					}
				},
				timers : [],
				fx : function(br, e, bs) {
					this.options = e;
					this.elem = br;
					this.prop = bs;
					e.orig = e.orig || {}
				}
			});
	b.fx.prototype = {
		update : function() {
			if (this.options.step) {
				this.options.step.call(this.elem, this.now, this)
			}
			(b.fx.step[this.prop] || b.fx.step._default)(this)
		},
		cur : function() {
			if (this.elem[this.prop] != null
					&& (!this.elem.style || this.elem.style[this.prop] == null)) {
				return this.elem[this.prop]
			}
			var e, br = b.css(this.elem, this.prop);
			return isNaN(e = parseFloat(br)) ? !br || br === "auto" ? 0 : br
					: e
		},
		custom : function(bv, bu, bt) {
			var e = this, bs = b.fx;
			this.startTime = a0 || bc();
			this.start = bv;
			this.end = bu;
			this.unit = bt || this.unit || (b.cssNumber[this.prop] ? "" : "px");
			this.now = this.start;
			this.pos = this.state = 0;
			function br(bw) {
				return e.step(bw)
			}
			br.elem = this.elem;
			if (br() && b.timers.push(br) && !aZ) {
				aZ = setInterval(bs.tick, bs.interval)
			}
		},
		show : function() {
			this.options.orig[this.prop] = b.style(this.elem, this.prop);
			this.options.show = true;
			this.custom(
					this.prop === "width" || this.prop === "height" ? 1 : 0,
					this.cur());
			b(this.elem).show()
		},
		hide : function() {
			this.options.orig[this.prop] = b.style(this.elem, this.prop);
			this.options.hide = true;
			this.custom(this.cur(), 0)
		},
		step : function(bu) {
			var bt = a0 || bc(), e = true, bv = this.elem, br = this.options, bs, bx;
			if (bu || bt >= br.duration + this.startTime) {
				this.now = this.end;
				this.pos = this.state = 1;
				this.update();
				br.animatedProperties[this.prop] = true;
				for (bs in br.animatedProperties) {
					if (br.animatedProperties[bs] !== true) {
						e = false
					}
				}
				if (e) {
					if (br.overflow != null && !b.support.shrinkWrapBlocks) {
						b.each( [ "", "X", "Y" ], function(by, bz) {
							bv.style["overflow" + bz] = br.overflow[by]
						})
					}
					if (br.hide) {
						b(bv).hide()
					}
					if (br.hide || br.show) {
						for ( var bw in br.animatedProperties) {
							b.style(bv, bw, br.orig[bw])
						}
					}
					br.complete.call(bv)
				}
				return false
			} else {
				if (br.duration == Infinity) {
					this.now = bt
				} else {
					bx = bt - this.startTime;
					this.state = bx / br.duration;
					this.pos = b.easing[br.animatedProperties[this.prop]](
							this.state, bx, 0, 1, br.duration);
					this.now = this.start
							+ ((this.end - this.start) * this.pos)
				}
				this.update()
			}
			return true
		}
	};
	b.extend(b.fx,
			{
				tick : function() {
					for ( var br = b.timers, e = 0; e < br.length; ++e) {
						if (!br[e]()) {
							br.splice(e--, 1)
						}
					}
					if (!br.length) {
						b.fx.stop()
					}
				},
				interval : 13,
				stop : function() {
					clearInterval(aZ);
					aZ = null
				},
				speeds : {
					slow : 600,
					fast : 200,
					_default : 400
				},
				step : {
					opacity : function(e) {
						b.style(e.elem, "opacity", e.now)
					},
					_default : function(e) {
						if (e.elem.style && e.elem.style[e.prop] != null) {
							e.elem.style[e.prop] = (e.prop === "width"
									|| e.prop === "height" ? Math.max(0, e.now)
									: e.now)
									+ e.unit
						} else {
							e.elem[e.prop] = e.now
						}
					}
				}
			});
	if (b.expr && b.expr.filters) {
		b.expr.filters.animated = function(e) {
			return b.grep(b.timers, function(br) {
				return e === br.elem
			}).length
		}
	}
	function x(bt) {
		if (!Q[bt]) {
			var e = ap.body, br = b("<" + bt + ">").appendTo(e), bs = br
					.css("display");
			br.remove();
			if (bs === "none" || bs === "") {
				if (!a4) {
					a4 = ap.createElement("iframe");
					a4.frameBorder = a4.width = a4.height = 0
				}
				e.appendChild(a4);
				if (!m || !a4.createElement) {
					m = (a4.contentWindow || a4.contentDocument).document;
					m.write((ap.compatMode === "CSS1Compat" ? "<!doctype html>"
							: "")
							+ "<html><body>");
					m.close()
				}
				br = m.createElement(bt);
				m.body.appendChild(br);
				bs = b.css(br, "display");
				e.removeChild(a4)
			}
			Q[bt] = bs
		}
		return Q[bt]
	}
	var T = /^t(?:able|d|h)$/i, Z = /^(?:body|html)$/i;
	if ("getBoundingClientRect" in ap.documentElement) {
		b.fn.offset = function(bE) {
			var bu = this[0], bx;
			if (bE) {
				return this.each(function(e) {
					b.offset.setOffset(this, bE, e)
				})
			}
			if (!bu || !bu.ownerDocument) {
				return null
			}
			if (bu === bu.ownerDocument.body) {
				return b.offset.bodyOffset(bu)
			}
			try {
				bx = bu.getBoundingClientRect()
			} catch (bB) {
			}
			var bD = bu.ownerDocument, bs = bD.documentElement;
			if (!bx || !b.contains(bs, bu)) {
				return bx ? {
					top : bx.top,
					left : bx.left
				} : {
					top : 0,
					left : 0
				}
			}
			var by = bD.body, bz = aF(bD), bw = bs.clientTop || by.clientTop
					|| 0, bA = bs.clientLeft || by.clientLeft || 0, br = bz.pageYOffset
					|| b.support.boxModel && bs.scrollTop || by.scrollTop, bv = bz.pageXOffset
					|| b.support.boxModel && bs.scrollLeft || by.scrollLeft, bC = bx.top
					+ br - bw, bt = bx.left + bv - bA;
			return {
				top : bC,
				left : bt
			}
		}
	} else {
		b.fn.offset = function(bB) {
			var bv = this[0];
			if (bB) {
				return this.each(function(bC) {
					b.offset.setOffset(this, bB, bC)
				})
			}
			if (!bv || !bv.ownerDocument) {
				return null
			}
			if (bv === bv.ownerDocument.body) {
				return b.offset.bodyOffset(bv)
			}
			b.offset.initialize();
			var by, bs = bv.offsetParent, br = bv, bA = bv.ownerDocument, bt = bA.documentElement, bw = bA.body, bx = bA.defaultView, e = bx ? bx
					.getComputedStyle(bv, null)
					: bv.currentStyle, bz = bv.offsetTop, bu = bv.offsetLeft;
			while ((bv = bv.parentNode) && bv !== bw && bv !== bt) {
				if (b.offset.supportsFixedPosition && e.position === "fixed") {
					break
				}
				by = bx ? bx.getComputedStyle(bv, null) : bv.currentStyle;
				bz -= bv.scrollTop;
				bu -= bv.scrollLeft;
				if (bv === bs) {
					bz += bv.offsetTop;
					bu += bv.offsetLeft;
					if (b.offset.doesNotAddBorder
							&& !(b.offset.doesAddBorderForTableAndCells && T
									.test(bv.nodeName))) {
						bz += parseFloat(by.borderTopWidth) || 0;
						bu += parseFloat(by.borderLeftWidth) || 0
					}
					br = bs;
					bs = bv.offsetParent
				}
				if (b.offset.subtractsBorderForOverflowNotVisible
						&& by.overflow !== "visible") {
					bz += parseFloat(by.borderTopWidth) || 0;
					bu += parseFloat(by.borderLeftWidth) || 0
				}
				e = by
			}
			if (e.position === "relative" || e.position === "static") {
				bz += bw.offsetTop;
				bu += bw.offsetLeft
			}
			if (b.offset.supportsFixedPosition && e.position === "fixed") {
				bz += Math.max(bt.scrollTop, bw.scrollTop);
				bu += Math.max(bt.scrollLeft, bw.scrollLeft)
			}
			return {
				top : bz,
				left : bu
			}
		}
	}
	b.offset = {
		initialize : function() {
			var e = ap.body, br = ap.createElement("div"), bu, bw, bv, bx, bs = parseFloat(b
					.css(e, "marginTop")) || 0, bt = "<div style='position:absolute;top:0;left:0;margin:0;border:5px solid #000;padding:0;width:1px;height:1px;'><div></div></div><table style='position:absolute;top:0;left:0;margin:0;border:5px solid #000;padding:0;width:1px;height:1px;' cellpadding='0' cellspacing='0'><tr><td></td></tr></table>";
			b.extend(br.style, {
				position : "absolute",
				top : 0,
				left : 0,
				margin : 0,
				border : 0,
				width : "1px",
				height : "1px",
				visibility : "hidden"
			});
			br.innerHTML = bt;
			e.insertBefore(br, e.firstChild);
			bu = br.firstChild;
			bw = bu.firstChild;
			bx = bu.nextSibling.firstChild.firstChild;
			this.doesNotAddBorder = (bw.offsetTop !== 5);
			this.doesAddBorderForTableAndCells = (bx.offsetTop === 5);
			bw.style.position = "fixed";
			bw.style.top = "20px";
			this.supportsFixedPosition = (bw.offsetTop === 20 || bw.offsetTop === 15);
			bw.style.position = bw.style.top = "";
			bu.style.overflow = "hidden";
			bu.style.position = "relative";
			this.subtractsBorderForOverflowNotVisible = (bw.offsetTop === -5);
			this.doesNotIncludeMarginInBodyOffset = (e.offsetTop !== bs);
			e.removeChild(br);
			b.offset.initialize = b.noop
		},
		bodyOffset : function(e) {
			var bs = e.offsetTop, br = e.offsetLeft;
			b.offset.initialize();
			if (b.offset.doesNotIncludeMarginInBodyOffset) {
				bs += parseFloat(b.css(e, "marginTop")) || 0;
				br += parseFloat(b.css(e, "marginLeft")) || 0
			}
			return {
				top : bs,
				left : br
			}
		},
		setOffset : function(bt, bC, bw) {
			var bx = b.css(bt, "position");
			if (bx === "static") {
				bt.style.position = "relative"
			}
			var bv = b(bt), br = bv.offset(), e = b.css(bt, "top"), bA = b.css(
					bt, "left"), bB = (bx === "absolute" || bx === "fixed")
					&& b.inArray("auto", [ e, bA ]) > -1, bz = {}, by = {}, bs, bu;
			if (bB) {
				by = bv.position();
				bs = by.top;
				bu = by.left
			} else {
				bs = parseFloat(e) || 0;
				bu = parseFloat(bA) || 0
			}
			if (b.isFunction(bC)) {
				bC = bC.call(bt, bw, br)
			}
			if (bC.top != null) {
				bz.top = (bC.top - br.top) + bs
			}
			if (bC.left != null) {
				bz.left = (bC.left - br.left) + bu
			}
			if ("using" in bC) {
				bC.using.call(bt, bz)
			} else {
				bv.css(bz)
			}
		}
	};
	b.fn
			.extend( {
				position : function() {
					if (!this[0]) {
						return null
					}
					var bs = this[0], br = this.offsetParent(), bt = this
							.offset(), e = Z.test(br[0].nodeName) ? {
						top : 0,
						left : 0
					} : br.offset();
					bt.top -= parseFloat(b.css(bs, "marginTop")) || 0;
					bt.left -= parseFloat(b.css(bs, "marginLeft")) || 0;
					e.top += parseFloat(b.css(br[0], "borderTopWidth")) || 0;
					e.left += parseFloat(b.css(br[0], "borderLeftWidth")) || 0;
					return {
						top : bt.top - e.top,
						left : bt.left - e.left
					}
				},
				offsetParent : function() {
					return this
							.map(function() {
								var e = this.offsetParent || ap.body;
								while (e
										&& (!Z.test(e.nodeName) && b.css(e,
												"position") === "static")) {
									e = e.offsetParent
								}
								return e
							})
				}
			});
	b.each( [ "Left", "Top" ], function(br, e) {
		var bs = "scroll" + e;
		b.fn[bs] = function(bv) {
			var bt, bu;
			if (bv === K) {
				bt = this[0];
				if (!bt) {
					return null
				}
				bu = aF(bt);
				return bu ? ("pageXOffset" in bu) ? bu[br ? "pageYOffset"
						: "pageXOffset"] : b.support.boxModel
						&& bu.document.documentElement[bs]
						|| bu.document.body[bs] : bt[bs]
			}
			return this.each(function() {
				bu = aF(this);
				if (bu) {
					bu.scrollTo(!br ? bv : b(bu).scrollLeft(), br ? bv : b(bu)
							.scrollTop())
				} else {
					this[bs] = bv
				}
			})
		}
	});
	function aF(e) {
		return b.isWindow(e) ? e : e.nodeType === 9 ? e.defaultView
				|| e.parentWindow : false
	}
	b
			.each(
					[ "Height", "Width" ],
					function(br, e) {
						var bs = e.toLowerCase();
						b.fn["inner" + e] = function() {
							var bt = this[0];
							return bt && bt.style ? parseFloat(b.css(bt, bs,
									"padding")) : null
						};
						b.fn["outer" + e] = function(bu) {
							var bt = this[0];
							return bt && bt.style ? parseFloat(b.css(bt, bs,
									bu ? "margin" : "border")) : null
						};
						b.fn[bs] = function(bv) {
							var bw = this[0];
							if (!bw) {
								return bv == null ? null : this
							}
							if (b.isFunction(bv)) {
								return this.each(function(bA) {
									var bz = b(this);
									bz[bs](bv.call(this, bA, bz[bs]()))
								})
							}
							if (b.isWindow(bw)) {
								var bx = bw.document.documentElement["client"
										+ e], bt = bw.document.body;
								return bw.document.compatMode === "CSS1Compat"
										&& bx || bt && bt["client" + e] || bx
							} else {
								if (bw.nodeType === 9) {
									return Math.max(bw.documentElement["client"
											+ e], bw.body["scroll" + e],
											bw.documentElement["scroll" + e],
											bw.body["offset" + e],
											bw.documentElement["offset" + e])
								} else {
									if (bv === K) {
										var by = b.css(bw, bs), bu = parseFloat(by);
										return b.isNaN(bu) ? by : bu
									} else {
										return this.css(bs,
												typeof bv === "string" ? bv
														: bv + "px")
									}
								}
							}
						}
					});
	a7.jQuery = a7.$ = b
})(window);