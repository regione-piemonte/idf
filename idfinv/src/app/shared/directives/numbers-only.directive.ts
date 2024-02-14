/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
	selector: '[OnlyNumber]'
})
export class NumbersOnlyDirective {
	@Input() OnlyNumber: boolean;
	regexStr = '^[0-9]*$';

	constructor(private el: ElementRef) {}

	@HostListener('keydown', ['$event']) onKeyDown(event) {
		let e = <KeyboardEvent>event;
		if (this.OnlyNumber) {
			if (
				[46, 8, 9, 27, 13, 110, 190].indexOf(e.keyCode) !== -1 ||
				// Allow: Ctrl+A
				(e.keyCode === 65 && (e.ctrlKey || e.metaKey)) ||
				// Allow: Ctrl+C
				(e.keyCode === 67 && (e.ctrlKey || e.metaKey)) ||
				// Allow: Ctrl+V
				// (e.keyCode === 86 && (e.ctrlKey || e.metaKey)) ||
				// Allow: Ctrl+X
				(e.keyCode === 88 && (e.ctrlKey || e.metaKey)) ||
				// Allow: home, end, left, right
				(e.keyCode >= 35 && e.keyCode <= 39)
			) {
				// let it happen, don't do anything
				return;
			}
			// Ensure that it is a number and stop the keypress
			if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
				e.preventDefault();
			}
			let ch = String.fromCharCode(e.keyCode);
			let regEx = new RegExp(this.regexStr);
			if (regEx.test(ch)) return;
			else {
				e.preventDefault();
			}
		}
	}

	validateValue(value: string): void {
		// test number with regular expression, when
		// number is invalid, replace it with a zero
		let valid: boolean = new RegExp(this.regexStr).test(value);
		this.el.nativeElement['value'] = valid ? value : 0;
	}

	// @HostListener('input', ['$event']) onInputChange(event) {
	//   const initalValue = this._el.nativeElement.value;
	//   this._el.nativeElement.value = initalValue.replace(/[^0-9]*/g, '');
	//   if ( initalValue !== this._el.nativeElement.value) {
	//     event.stopPropagation();
	//   }
	// }

	@HostListener('paste', ['$event']) blockPaste(e) {
		// e.preventDefault();
		// console.log('SRC EL',e.srcElement['value']);
		let value = e.clipboardData.getData('text/plain');
		let event = <KeyboardEvent>e;
		let valid: boolean = new RegExp(this.regexStr).test(value);
		this.el.nativeElement['value'] = valid ? value : null;
		// if (this.el.nativeElement['value'] = valid) {
		// 	this.el.nativeElement['value'] = value;
		// } else {
		// 	null;
		// }
		e.preventDefault();
	}
}
