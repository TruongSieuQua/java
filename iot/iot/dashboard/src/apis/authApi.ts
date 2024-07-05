
const API_URL = `${process.env.SENSOR_API || "http://localhost:8082"}/auth`;

export const login = async ({ email, password }: { email: string; password: string }) => {
  try {
    const response = await fetch(`${API_URL}/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ email, password }),
    });
    if (response.ok) {
      return {
        success: true,
        data: await response.json(),
      };
    } else {
      return {
        success: false,
        error: await response.json(),
      };
    }
  } catch (e: any) {
    console.log(e);
    return {
      success: false,
      error: { message: e.message },
    };
  }
}
