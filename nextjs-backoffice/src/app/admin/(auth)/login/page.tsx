"use client";

export default function Login(){
	return <div>
		<form action="/action_page.php" method="post">
  <input
    className="form-control"
    type="email"
    required
    placeholder="Username"
    onInvalid={(event) => { event.currentTarget.setCustomValidity('Please enter a valid email'); }}
    onInput={(event) => { event.currentTarget.setCustomValidity(''); }}
  />
  <input type="submit" value="Submit" />
</form>
	</div>
}
