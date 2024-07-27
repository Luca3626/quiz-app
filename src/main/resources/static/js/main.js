onload = () => {
	let form = document.getElementById("form")
	form.onsubmit = (e) => {
		let btn = document.getElementById("btnDettagli")
		if (btn == null) {
			let check = document.getElementsByName("check")
			let flag = false
			check.forEach(ele => {
				if (ele.checked)
					flag = true

			})
			if (!flag) {
				e.preventDefault()

			}
		}

	}

}